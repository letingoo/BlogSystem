package user.service;

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


    public void addUser(User user) {

        userMapper.addUser(user);
    }


    public User doLogin(String userName, String password) {

        HashMap<String, String> map = new HashMap<String, String>();
        map.put("userName", userName);
        map.put("password", password);

        return userMapper.selectUser(map);
    }



}
