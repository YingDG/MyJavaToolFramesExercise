package yingdg.exercise.rabbitmq.broadcast;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by yingdg on 2018/1/15 0015.
 */
public class FanoutLogMessage {
    private static final String EXCHANGE_NAME = "logs";

    /*
    广播的模式进行消息的发送
     */
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        //fanout表示分发，所有的消费者得到同样的队列信息
        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");

        //分发信息
        for (int i = 0; i < 5; i++) {
            String message = "This is MyWorld" + i;
            channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes());
            System.out.println("Log Sent:" + message);
        }

        channel.close();
        connection.close();
    }
}
