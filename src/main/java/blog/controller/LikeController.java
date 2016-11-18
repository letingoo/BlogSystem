package blog.controller;

import blog.service.BlogService;
import com.alibaba.druid.filter.AutoLoad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by letingoo on 2016/11/10.
 *
 * 负责点赞的Controller
 */
@Controller
public class LikeController {


    @Autowired
    private BlogService blogService;


    @RequestMapping("/like/{blogId}")
    public String likeBlog(@PathVariable("blogId") int blogId) {

        blogService.likeBlog(blogId);
        return "success";
    }

}
