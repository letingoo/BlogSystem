package blog.controller;

import blog.entity.Blog;
import blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import user.entity.User;
import util.PageParam;

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
    public String toAddBlog(HttpSession session) {

        if ( session.getAttribute("user") == null )
            return "redirect:/user/toLogin";
        else
            return "addBlog";
    }


    /**
     * 增加Blog
     * @param session
     * @param blog
     * @return
     */
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/blog")
    public String addBlog(HttpSession session, Blog blog) {

        if ( session.getAttribute("user") == null )
            return "fail";

        blog.setUser( (User) session.getAttribute("user") );
        service.addBlog(blog);

        return "success";
    }


    /**
     * 获取博客列表
     * @param pageNo
     * @param pageSize
     * @return
     */
    @RequestMapping("/blogs/{pageNo}/{pageSize}")
    public ModelAndView getBlogs(
            @PathVariable(value = "pageNo") int pageNo,
            @PathVariable(value = "pageSize")  int pageSize) {


        ModelAndView modelAndView = new ModelAndView();
        PageParam pageParam = new PageParam(pageNo, pageSize);
        modelAndView.addObject( "blogs", service.getBlogs(pageParam) );
        modelAndView.setViewName("blogs");

        return modelAndView;
    }


    /**
     *
     * @param blogId
     * @return
     */
    @RequestMapping("/blogDetail/{blogId}")
    public ModelAndView getBlogDetail(@PathVariable int blogId) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject( "blog", service.getBlogDetail(blogId) );
        modelAndView.setViewName("blogDetail");

        return modelAndView;
    }


    /**
     * 查询一个用户所发的Blog
     * @param userName
     * @param pageNo
     * @param pageSize
     * @return
     */
    @RequestMapping("/u/{userName}/{pageNo}/{pageSize}")
    public ModelAndView getUserBlogs(@PathVariable("userName") String userName,
                                     @PathVariable("pageNo") int pageNo,
                                     @PathVariable("pageSize") int pageSize) {

        ModelAndView modelAndView = new ModelAndView();

        PageParam pageParam = new PageParam(pageNo, pageSize);
        modelAndView.addObject("blogs", service.getBlogsByUser(pageParam, userName));
        modelAndView.setViewName("blogs");

        return modelAndView;
    }

}
