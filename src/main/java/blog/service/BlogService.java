package blog.service;

import blog.dao.BlogMapper;
import blog.entity.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Service;
import util.PageParam;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by letingoo on 2016/10/24.
 */

@Service
public class BlogService {


    @Autowired
    private BlogMapper mapper;


    @Autowired
    private RedisTemplate<String, Integer> redisTemplate;

    public void addBlog(Blog blog) {
        mapper.insertBlog(blog);
    }



    public List<Blog> getBlogs(PageParam pageParam) {

        Map<String, Object> searchMap = new HashMap<String, Object>();
        searchMap.put("pageParam", pageParam);
        searchMap.put( "blog", new Blog() );

        return mapper.getBlogs(searchMap);

    }



    public List<Blog> getBlogsByUser(PageParam pageParam, String userName) {

        Map<String, Object> searchMap = new HashMap<String, Object>();
        searchMap.put("pageParam", pageParam);
        Blog blog = new Blog();
        blog.setUserName( userName );
        searchMap.put("blog", blog);

        List<Blog> result = mapper.getBlogs(searchMap);
        return result;
    }



    public Blog getBlogDetail(int blogId) {

        return mapper.getBlogDetail(blogId);
    }


    /**
     * 阈值，大于该阈值写入MySql
     */
    private static final int LIKES_ENOUGH = 10;

    /**
     * 对Blog进行点赞
     * 并不直接和数据库交互，先和Redis读写，当满足一定的阈值时再写入数据库
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

                mapper.incLikes(updateMap);
            }

            else {
                boundZSetOperations.incrementScore(blogId, 1);
            }
        }


        return "success";
    }



}
