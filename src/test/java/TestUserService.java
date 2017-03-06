
import blog.entity.Blog;
import blog.service.BlogService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import rabbitmq.ProducerTest;
import redis.clients.jedis.Jedis;
import timeline.service.TimelineService;
import user.entity.User;
import user.service.UserService;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by letingoo on 2016/10/9.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring.xml", "classpath*:mybatis.xml" })
public class TestUserService {

    @Resource
    private AmqpTemplate amqpTemplate;

    @Test
    public void testLikeBlog() {

        String str = "message123";
        amqpTemplate.convertAndSend("queueTestKey", str);


    }


}
