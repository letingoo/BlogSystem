package user.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import user.dao.UserMapper;
import user.entity.User;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by letingoo on 2016/10/3.
 */

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;


    // MD5加密
    private static MessageDigest md5;

    static {
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }



    private static final Logger logger = Logger.getLogger(UserService.class);



    public void addUser(User user) {

        String password = user.getPassword();
        md5.update(password.getBytes());
        String password_md5 = new BigInteger(1, md5.digest()).toString(16);
        user.setPassword( password_md5 );

        userMapper.addUser(user);
    }


    public User doLogin(String userName, String password) {

        // 先将password用MD5加密
        md5.update(password.getBytes());
        String password_md5 = new BigInteger(1, md5.digest()).toString(16);


        HashMap<String, String> map = new HashMap<String, String>();
        map.put("userName", userName);
        map.put("password", password_md5);

        return userMapper.selectUser(map);
    }



    public void updateUser(User user) {

        userMapper.updateUser(user);
    }


}
