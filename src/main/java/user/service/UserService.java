package user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import user.dao.UserMapper;
import user.entity.User;

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





}
