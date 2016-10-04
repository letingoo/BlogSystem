package user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import user.dao.UserRepository;
import user.entity.User;

/**
 * Created by letingoo on 2016/10/3.
 */

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public void addUser(User user) {

        userRepository.addUser(user);
    }

}
