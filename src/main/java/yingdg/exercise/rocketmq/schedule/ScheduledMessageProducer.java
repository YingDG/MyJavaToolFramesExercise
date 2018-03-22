package yingdg.exercise.rocketmq.schedule;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

/**
 * Created by zdm on 2018/3/21.
 */
public class ScheduledMessageProducer {
    public static void main(String[] args) {
        DefaultMQProducer producer = new DefaultMQProducer("ExampleProducerGroup");

        try {
            producer.start();

            for (int i = 0; i < 100; i++) {
                Message message = new Message(
                        "TestTopic", ("Hello scheduled message " + i).getBytes());
                // This message will be delivered to consumer 10 seconds later.
                message.setDelayTimeLevel(3);
                producer.send(message);
            }
        } catch (InterruptedException | RemotingException | MQBrokerException | MQClientException e) {
            e.printStackTrace();
        } finally {
            producer.shutdown();
        }
    }
}
