package blog.service;

import blog.dao.BlogMapper;
import blog.entity.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import util.PageParam;

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



    public List<Blog> getBlogs(PageParam pageParam) {

        Map<String, Object> searchMap = new HashMap<String, Object>();
        searchMap.put("pageParam", pageParam);
        searchMap.put( "blog", new Blog() );

        return mapper.getBlogs(searchMap);

    }



    public List<Blog> getBlogsByUser(PageParam pageParam, String userName) {

        Map<String, Object> searchMap = new HashMap<String, Object>();
        searchMap.put("pageParam", pageParam);
        Blog blog = new Blog();
        blog.setUserName( userName );
        searchMap.put("blog", blog);

        List<Blog> result = mapper.getBlogs(searchMap);
        return result;
    }



    public Blog getBlogDetail(int blogId) {

        return mapper.getBlogDetail(blogId);
    }



}
