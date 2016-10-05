package user.dao;

import org.springframework.stereotype.Repository;
import user.entity.User;

/**
 * Created by letingoo on 2016/10/3.
 */

@Repository
public interface UserMapper {

    /**
     * 添加用户
     */
    public void addUser(User user);


    /**
     * 查找用户
     * @param userName 用户名
     * @param password  密码
     * @return
     */
    public User selectUser(String userName, String password);
}
