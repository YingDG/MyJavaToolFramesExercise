package yingdg.exercise.rocketmq.broadcast;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.io.UnsupportedEncodingException;

/**
 * Created by zdm on 2018/3/21.
 */
public class BroadcastProducer {
    public static void main(String[] args) {
        DefaultMQProducer producer = new DefaultMQProducer("ProducerGroupName");

        try {
            producer.start();

            for (int i = 0; i < 100; i++) {
                Message msg = new Message(
                        "TopicTest",
                        "TagA",
                        "OrderID188",
                        "Hello world".getBytes(RemotingHelper.DEFAULT_CHARSET));
                SendResult sendResult = producer.send(msg);
                System.out.printf("%s%n", sendResult);
            }
        } catch (MQClientException | InterruptedException | RemotingException | UnsupportedEncodingException | MQBrokerException e) {
            e.printStackTrace();
        } finally {
            producer.shutdown();
        }
    }
}
