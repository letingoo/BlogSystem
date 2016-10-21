
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import user.entity.User;
import user.service.UserService;

import java.util.List;

/**
 * Created by letingoo on 2016/10/9.
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"classpath*:spring.xml", "classpath*:mybatis.xml" })
public class TestUserService {

    @Autowired
    private UserService service;
//
//    @Test
//    public void testLogin() {
//
//
//        List<String> result = service.getPasswords();
//
//        for (String item : result)
//            System.out.println(item);
//    }

}
