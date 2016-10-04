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
}
