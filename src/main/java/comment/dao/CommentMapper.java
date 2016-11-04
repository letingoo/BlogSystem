package comment.dao;

import org.springframework.stereotype.Repository;
import comment.entity.Comment;

import java.util.List;

/**
 * Created by letingoo on 2016/11/3.
 */
@Repository
public interface CommentMapper {

    void addComment(Comment comment);

    List<Comment> getComments(int blogId);

}
