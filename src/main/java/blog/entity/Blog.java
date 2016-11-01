package blog.entity;

import user.entity.User;

import java.io.Serializable;

/**
 * Created by letingoo on 2016/10/24
 * 博客实体类.
 */
public class Blog implements Serializable{



    private int id;

    private String title;

    // 内容，暂时用String类型
    private String content;

    private User user;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
