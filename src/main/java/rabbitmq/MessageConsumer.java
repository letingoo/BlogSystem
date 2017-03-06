package rabbitmq;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

/**
 * Created by BASA on 2017/3/2.
 */
public class MessageConsumer implements MessageListener {

    @Override
    public void onMessage(Message message) {

        byte[] bytes = message.getBody();
        String str = new String(bytes);

        System.out.println("I have received message: " + str);
    }
}
