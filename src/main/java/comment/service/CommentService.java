package comment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import comment.dao.CommentMapper;
import comment.entity.Comment;

import java.util.List;

/**
 * Created by letingoo on 2016/11/3.
 */
@Service
public class CommentService {


    @Autowired
    private CommentMapper mapper;


    public List<Comment> getComments(int blogId) {

        return mapper.getComments(blogId);
    }



    public void addComment(Comment comment) {

        mapper.addComment(comment);
    }

}
