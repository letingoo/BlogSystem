package follow.dao;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by BASA on 2016/12/21.
 */

@Repository
public interface FollowMapper {


    void insertFollow(Map<String, String> followMap);

    List<String> getFollowee(String follower);

    int checkExist(Map<String, String> paramMap);



}
