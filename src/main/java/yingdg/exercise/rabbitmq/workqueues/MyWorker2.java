package yingdg.exercise.rabbitmq.workqueues;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by yingdg on 2018/1/15 0015.
 */
public class MyWorker2 {
    private static final String TASK_QUEUE_NAME = "work_list";

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(TASK_QUEUE_NAME, true, false, false, null);
        System.out.println("MyWorker2:Waiting for receiving messages");
        //每次从队列获取的数量
        channel.basicQos(1);

        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println("MyWorker2  Received:" + message);

                try {
//                    throw new Exception();
                    doWork(message);
                } catch (Exception e) {
                    channel.abort();
                } finally {
                    System.out.println("Worker2 Done");
                    channel.basicAck(envelope.getDeliveryTag(), false);
                }
            }
        };
        boolean autoAck = false;
        //消息消费完成确认
        channel.basicConsume(TASK_QUEUE_NAME, autoAck, consumer);
    }

    private static void doWork(String task) {
        try {
            Thread.sleep(1000); // 暂停1秒钟
        } catch (InterruptedException _ignored) {
            Thread.currentThread().interrupt();
        }
    }
}
