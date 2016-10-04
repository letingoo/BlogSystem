package user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import user.entity.User;
import user.service.UserService;

/**
 * Created by letingoo on 2016/10/3.
 */


@Controller
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserService userService;





    @RequestMapping("/toAddUser")
    public String toAddUser() {

        return "register";
    }


    /**
     * 添加用户
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/addUser")
    public String addUser(User user) {

        userService.addUser(user);
        return "hello";
    }


}
