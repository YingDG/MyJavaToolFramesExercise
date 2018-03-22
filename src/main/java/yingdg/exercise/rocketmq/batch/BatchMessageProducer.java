package yingdg.exercise.rocketmq.batch;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zdm on 2018/3/21.
 */
public class BatchMessageProducer {
    public static void main(String[] args) {
        DefaultMQProducer producer = new DefaultMQProducer("BatchMessage");

        try {
            producer.start();

            String topic = "BatchTest";
            List<Message> messages = new ArrayList<>();
            messages.add(new Message(topic, "TagA", "OrderID001", "Hello world 0".getBytes()));
            messages.add(new Message(topic, "TagA", "OrderID002", "Hello world 1".getBytes()));
            messages.add(new Message(topic, "TagA", "OrderID003", "Hello world 2".getBytes()));
            producer.send(messages);

            // 分批处理大流量消息数据
            // split the large list into small ones:
            /*
            ListSplitter splitter = new ListSplitter(messages);
            while (splitter.hasNext()) {
                List<Message> listItem = splitter.next();
                producer.send(listItem);
            }
            */
        } catch (MQClientException | InterruptedException | MQBrokerException | RemotingException e) {
            e.printStackTrace();
        } finally {
            producer.shutdown();
        }
    }
}
