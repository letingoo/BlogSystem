package user.dao;

import org.springframework.stereotype.Repository;
import user.entity.User;

import java.util.HashMap;
import java.util.List;

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
     * @return
     */
    public User selectUser(HashMap map);


    /**
     * 更新用户资料
     * @param user
     */
    public void updateUser(User user);


}
