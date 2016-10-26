package blog.dao;

import blog.entity.Blog;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by letingoo on 2016/10/24.
 */
@Repository
public interface BlogMapper {

    /**
     * 插入一条博客
     */
    void insertBlog(Blog blog);


    /**
     * 获取博客列表
     * @param map
     * @return
     */
    List<Blog> getBlogs(Map<String, String> map);

}
