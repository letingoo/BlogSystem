package timeline.service;

import blog.entity.Blog;
import com.google.gson.Gson;
import follow.dao.FollowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import util.GsonUtil;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by BASA on 2017/2/24.
 */

/**
 * 负责处理TImeline的相关，本来应该写在消息队列中，暂时先这样
 */
@Service
public class TimelineService {


    @Resource
    private Jedis jedis;

    @Value("${TIME_LINE_PREFIX}")
    private String TIME_LINE_PREFIX;

    @Autowired
    private FollowMapper followMapper;

    /**
     * “推”模式
     *  把followee发出的blog推送到follower的redis缓存中
     * @param blog
     */
    public void pushTimeLine(Blog blog) {

        List<String> followers = followMapper.getFollower( blog.getUser().getUserName() );

        jedis.auth("letingoo");

        for (String follower : followers) {
            jedis.zadd(TIME_LINE_PREFIX + follower, blog.getTime().getTime(), GsonUtil.useGson().toJson(blog));
        }

    }


    private static final int PAGE_SIZE = 10;


    /**
     * 获取用户的timeline
     * @param user
     * @param pageNo
     * @return
     */
    public List<Blog> getTimeLine(String user, int pageNo) {

        jedis.auth("letingoo");

        int start = pageNo * PAGE_SIZE;
        int end = (pageNo + 1) * PAGE_SIZE - 1;

        List<Blog> result = new ArrayList<Blog>();

        Set<String> timeLineSet = jedis.zrevrange(TIME_LINE_PREFIX + user, start, end);
        for (String timeLine : timeLineSet) {

            Blog blog = GsonUtil.useGson().fromJson(timeLine, Blog.class);
            result.add(blog);
        }

        return result;

    }


}
