package user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import user.entity.User;
import user.service.UserService;

import javax.servlet.http.HttpServletRequest;

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



    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/login")
    public String doLogin(HttpServletRequest request) {

        String userName = request.getParameter("userName");
        String password = request.getParameter("password");

        User user = userService.doLogin(userName, password);

        if ( null == user ) {
            return "fail";
        }

        else {
            request.getSession().setAttribute("user", user);
            return "success";
        }
    }

}
