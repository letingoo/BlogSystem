package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by letingoo on 2016/10/1.
 *  仅为测试SpringMVC环境是否搭建好
 */


@Controller
public class HelloController {



    public HelloController() {

        System.out.println("controller");
    }



    @RequestMapping(method = RequestMethod.GET, value = "/test")
    public String test() {

        return "hello";

    }


}
