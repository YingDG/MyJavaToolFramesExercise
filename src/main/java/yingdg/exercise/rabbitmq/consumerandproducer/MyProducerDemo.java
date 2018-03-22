package yingdg.exercise.rabbitmq.consumerandproducer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by yingdg on 2018/1/15 0015.
 */
public class MyProducerDemo {
    public final static String QUEUE_NAME = "rabbitmq.test";

    public static void main(String[] args) throws IOException, TimeoutException {
        //创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        //设置RabbitMQ相关信息
        factory.setHost("localhost");
        //创建一个新的连接
        Connection connection = factory.newConnection();
        //创建一个通道
        Channel channel = connection.createChannel();
        //  声明一个队列
//        channel.queueDeclare(QUEUE_NAME, false, false, true, null);

        String message = "Hello RabbitMQ";
        //发送消息到队列中
        channel.basicPublish(
                "", QUEUE_NAME, null, message.getBytes("UTF-8"));
        System.out.println("Producer Send:" + message);

        //关闭通道和连接
        channel.close();
        connection.close();
    }
}
