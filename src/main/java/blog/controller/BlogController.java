package blog.controller;

import blog.entity.Blog;
import blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import user.entity.User;

import javax.servlet.http.HttpSession;

/**
 * Created by letingoo on 2016/10/24.
 */


@Controller
@RequestMapping("/blog")
public class BlogController {


    @Autowired
    private BlogService service;


    @RequestMapping("/toAddBlog")
    public String toAddBlog() {
        return "addBlog";
    }


    /**
     * 增加Blog
     * @param session
     * @param blog
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/addBlog")
    public String addBlog(HttpSession session, Blog blog) {

        if ( session.getAttribute("user") == null )
            return "fail";

        blog.setUser( (User) session.getAttribute("user") );
        service.addBlog(blog);

        return "success";
    }


    /**
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    @RequestMapping("/showBlogs")
    public ModelAndView getBlogs(int pageNo, int pageSize) {


        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject( "blogs", service.getBlogs(pageNo, pageSize) );
        modelAndView.setViewName("blogs");

        return modelAndView;
    }


}
