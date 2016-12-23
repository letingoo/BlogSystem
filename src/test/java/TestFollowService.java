import follow.service.FollowService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by BASA on 2016/12/22.
 */

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"classpath*:spring.xml", "classpath*:mybatis.xml"})
public class TestFollowService {

    @Autowired
    private FollowService service;


//    @Test
//    public void testFollow() {
//
//        service.followSomeone("letingoo", "rrr");
//    }


}
