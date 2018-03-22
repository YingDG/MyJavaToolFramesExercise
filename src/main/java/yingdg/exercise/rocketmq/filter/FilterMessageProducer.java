package yingdg.exercise.rocketmq.filter;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.io.UnsupportedEncodingException;

/**
 * Created by zdm on 2018/3/21.
 */
public class FilterMessageProducer {
    public static void main(String[] args) {
        DefaultMQProducer producer = new DefaultMQProducer("Filter");

        try {
            producer.start();

            for (int i = 0; i < 5; i++) {
                Message message = new Message("filter", "a", "filter message".getBytes(RemotingHelper.DEFAULT_CHARSET));
                // Set some properties
                message.putUserProperty("a", Integer.toString(i));

                producer.send(message);
            }
        } catch (MQClientException | UnsupportedEncodingException | InterruptedException | MQBrokerException | RemotingException e) {
            e.printStackTrace();
        } finally {
            producer.shutdown();
        }
    }
}
