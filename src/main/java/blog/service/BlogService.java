package blog.service;

import blog.dao.BlogMapper;
import blog.entity.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.ManagedProperties;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by letingoo on 2016/10/24.
 */

@Service
public class BlogService {


    @Autowired
    private BlogMapper mapper;

    public void addBlog(Blog blog) {
        mapper.insertBlog(blog);
    }


    public List<Blog> getBlogs(int pageNo, int pageSize) {

        int start = ( pageNo - 1 ) * pageSize;
        int end = pageNo * pageSize;

        Map<String, String> map = new HashMap<String, String>();
        map.put("start", start + "");
        map.put("end", end + "");

        return mapper.getBlogs(map);

    }

}
