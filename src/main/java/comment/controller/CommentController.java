package comment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import comment.entity.Comment;
import comment.service.CommentService;
import user.entity.User;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * Created by letingoo on 2016/11/3.
 * 处理回帖的Controller
 * 尝试使用REST风格的url
 */

@RestController
@RequestMapping("/comment")
public class CommentController {


    @Autowired
    private CommentService service;



    /**
     * 根据blogId获取post列表
     * @param blogId
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/comments/{blogId}")
    public List<Comment> comments(@PathVariable("blogId") int blogId) {

        return service.getCommentss(blogId);
    }


    /**
     * 添加一条post
     * @param blogId
     * @param comment
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/comment/{blogId}")
    public String comment(HttpSession session, @PathVariable("blogId")int blogId, Comment comment) {

        if ( session.getAttribute("user") == null )
            return "fail";

        /*
         * 设置发帖人和发帖时间
         */
        User user = (User) session.getAttribute("user");
        comment.setUserName( user.getUserName() );
        comment.setTime( new Timestamp((new Date()).getTime()) );
        comment.setBlogId(blogId);

        service.addComment( comment );

        return "success";

    }





}
