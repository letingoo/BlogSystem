package user.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import user.dao.UserMapper;
import user.entity.User;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by letingoo on 2016/10/3.
 */

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;



    private static final Logger logger = Logger.getLogger(UserService.class);

    public void addUser(User user) {

        userMapper.addUser(user);
    }


    public User doLogin(String userName, String password) {

        HashMap<String, String> map = new HashMap<String, String>();
        map.put("userName", userName);
        map.put("password", password);

        logger.debug("Login");
        return userMapper.selectUser(map);
    }



}
