package rabbitmq.service;

/**
 * Created by BASA on 2017/3/2.
 */


import blog.entity.Blog;
import com.google.gson.*;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import timeline.service.TimelineService;
import util.GsonUtil;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.lang.reflect.Type;
import java.util.Date;

/**
 *     负责将blog推送到redis缓存上。消息队列中的Consumer
 */

@Service
public class PushTimelineConsumer implements MessageListener{


    @Autowired
    private TimelineService timelineService;

    @Override
    public void onMessage(Message message) {

        System.out.println("onMessage...");

        byte[] bytes = message.getBody();

        // 将byte数组转为Blog对象。

        final String json = new String(bytes);

        try{

            Blog blog = GsonUtil.useGsonForQueue().fromJson(json, Blog.class);

            timelineService.pushTimeLine(blog);
            System.out.println("suspect");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
