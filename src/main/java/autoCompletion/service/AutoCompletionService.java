package autoCompletion.service;

import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Created by BASA on 2017/4/4.
 */

@Service
public class AutoCompletionService {


    @Resource
    private Jedis jedis;

    private static final String ZSET_NAME = "mySearch";

    private static final int LENGTH = 5;



    public List<String> autoCompletion(String word) {

        if (word == null || word.length() < 2)
            return new ArrayList<String>();

        String prefix = word.substring(0, word.length() - 1) + (char)(word.charAt(word.length() - 1) - 1) + "{";


        String postfix = word + '{';

        jedis.auth("letingoo");
        jedis.zadd(ZSET_NAME, 0, prefix);
        jedis.zadd(ZSET_NAME, 0, postfix);

        int startIndex = (int) (jedis.zrank(ZSET_NAME, prefix) + 1);
        int endIndex = (int) (jedis.zrank(ZSET_NAME, postfix) - 1);


        endIndex = (startIndex + LENGTH > endIndex) ? endIndex : startIndex + LENGTH;
        Set<String> set = jedis.zrange( ZSET_NAME, startIndex, endIndex);

        jedis.zrem(ZSET_NAME, prefix);
        jedis.zrem(ZSET_NAME, postfix);


        List<String> result = new LinkedList<String>();

        for (String item : set)
            result.add(item);

        return result;

    }
}
