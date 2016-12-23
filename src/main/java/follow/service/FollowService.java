package follow.service;

import follow.dao.FollowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by BASA on 2016/12/21.
 */
@Service
public class FollowService {


    @Autowired
    private FollowMapper followMapper;



    /**
     * 关注某人
     * @param follower 关注着
     * @param followee 被关注者
     * @return
     */
    public String followSomeone(String follower, String followee) {


        try {

            Map<String, String> paramMap = new HashMap<String, String>();
            paramMap.put("follower", follower);
            paramMap.put("followee", followee);

            if (followMapper.checkExist(paramMap) > 0)
                return "only once";

            followMapper.insertFollow(paramMap);
            return "success";

        } catch (Exception e) {
            return "fail";
        }
    }



    public List<String> getFollowee(String follower) {
        return followMapper.getFollowee(follower);
    }

}
