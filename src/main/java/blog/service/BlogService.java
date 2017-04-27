package blog.service;

import blog.dao.BlogMapper;
import blog.entity.Blog;
import follow.dao.FollowMapper;
import follow.service.FollowService;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Service;
import timeline.service.TimelineService;
import util.PageParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by letingoo on 2016/10/24.
 */

@Service
public class BlogService {


    @Autowired
    private BlogMapper blogMapper;

    @Autowired
    private RedisTemplate<String, Integer> redisTemplate;


    @Autowired
    private AmqpTemplate amqpTemplate;

    public void addBlog(Blog blog) {

        blogMapper.insertBlog(blog);

        // 把blog推送到关注者的timeline的Redis缓存上。使用消息队列
        amqpTemplate.convertAndSend("pushTimelineQueueKey", blog);

        System.out.println("add Blog done");
    }



    public List<Blog> getBlogs(PageParam pageParam) {

        Map<String, Object> searchMap = new HashMap<String, Object>();
        searchMap.put("pageParam", pageParam);
        searchMap.put( "blog", new Blog() );

        return blogMapper.getBlogs(searchMap);

    }



    public List<Blog> getBlogsByUser(PageParam pageParam, String userName) {

        Map<String, Object> searchMap = new HashMap<String, Object>();
        searchMap.put("pageParam", pageParam);
        Blog blog = new Blog();
        blog.setUserName( userName );
        searchMap.put("blog", blog);

        List<Blog> result = blogMapper.getBlogs(searchMap);
        return result;
    }



    public Blog getBlogDetail(int blogId) {


        Blog blog = blogMapper.getBlogDetail(blogId);

        int db_likes = blog.getLikes();

        // 从Redis中读取likes值
        BoundZSetOperations<String, Integer> boundZSetOperations = redisTemplate.boundZSetOps("likes");

        Double score = boundZSetOperations.score(blogId);
        if (score != null) {
            int cache_likes = score.intValue();
            blog.setLikes( db_likes + cache_likes );
        }

        else
            blog.setLikes( db_likes );

        return blog;

    }




    public String deleteBlog(int blogId, String deleter) {

        String userName = blogMapper.getUserName(blogId);
        if (!userName.equals(deleter))
            return "You can't delete it";


        blogMapper.deleteBlog(blogId);
        return "delete success";
    }




    /**
     * 阈值，大于该阈值写入MySql
     */
    private static final int LIKES_ENOUGH = 10;

    /**
     * 对Blog进行点赞
     * 并不直接和数据库交互，先和Redis读写，当满足一定的阈值时再写入数据库
     *
     * 后续应该写定时任务，定时将redis中的数据写入MySql中。
     * @param blogId
     * @return
     */
    public String likeBlog(int blogId) {

        BoundZSetOperations<String, Integer> boundZSetOperations = redisTemplate.boundZSetOps("likes");
        if (null == boundZSetOperations.score(blogId))
            boundZSetOperations.add(blogId, 1);
        else {
            int redis_likes = boundZSetOperations.score(blogId).intValue();
            if (redis_likes >= LIKES_ENOUGH) {
                boundZSetOperations.remove(blogId);
                Map<String, Integer> updateMap = new HashMap<String, Integer>();
                updateMap.put("blogId", blogId);
                updateMap.put("likes", redis_likes + 1);

                blogMapper.incLikes(updateMap);
            }

            else {
                boundZSetOperations.incrementScore(blogId, 1);
            }
        }


        return "success";
    }


    /**
     * 获取blog的like的数量。从Redis和MySql中一起读，取和。
     * @param blogId
     * @return
     */
//    public int getBlogLikes(int blogId) {
//
//        // MySql中的likes值
//        int db_likes = blogMapper.getLikes(blogId);
//
//
//        // 从Redis中读取likes值
//        BoundZSetOperations<String, Integer> boundZSetOperations = redisTemplate.boundZSetOps("likes");
//        int cache_likes = (boundZSetOperations.score(blogId)).intValue();
//
//        return db_likes + cache_likes;
//    }





}
