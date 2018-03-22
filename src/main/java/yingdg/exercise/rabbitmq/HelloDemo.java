package yingdg.exercise.rabbitmq;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.TimeoutException;

/**
 * Created by yingdg on 2018/1/15 0015.
 */
public class HelloDemo {
    private static final String QUEUE_NAME = "hello";
    private static ConnectionFactory factory;

    static {
        factory = new ConnectionFactory();
        factory.setUsername("guest");
        factory.setPassword("guest");
        factory.setHost("localhost"); // http:localhost:15672 用户名和密码默认都为guest
        factory.setVirtualHost("/"); // 虚拟主机
        factory.setPort(5672);
    }

    /*
    RabbitMQ基于AMDQ协议
     */
    public static void main(String[] args) {
        Connection connection = null;
        Channel channel = null;
        try {
             /*
            从Channel可以构造出一个消费者Consumer用来监听收到的消息，也可以使用channel发布消息。
            RabbitMQ的消息是byte[]，所以需要将字串转成字节数组才能发送。
             */
            connection = factory.newConnection();
            channel = connection.createChannel();
            /*
            queueDeclare
            第一个参数表示队列名称、
            第二个参数为是否持久化（true表示是，队列将在服务器重启时生存）、
            第三个参数为是否是独占队列（创建者可以使用的私有队列，断开后自动删除）、
            第四个参数为当所有消费者客户端连接断开时是否自动删除队列、
            第五个参数为队列的其他参数
             */
            channel.queueDeclare(QUEUE_NAME, true, false, false, null);

            /*
            Hello
             */
            Consumer consumer = new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope,
                                           AMQP.BasicProperties properties, byte[] body) throws IOException {
                    String message = new String(body, "UTF-8");
                    System.out.println("Client Received '" + message + "'");
                }
            };
            channel.basicConsume(QUEUE_NAME, true, consumer);
            channel.queueDeclare(QUEUE_NAME, true, false, false, null);

            String message = "Hello RabbitMQ!";
            /*
            Producer只能发送到exchange，不能直接发送到queue
            basicPublish
            第一个参数为交换机名称、
            第二个参数为队列映射的路由key、
            第三个参数为消息的其他属性、
            第四个参数为发送信息的主体
             */
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println("Server Sent '" + message + "'");

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        } catch (TimeoutException | IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (Objects.nonNull(channel)) {
                    channel.close();
                }
                if (Objects.nonNull(connection)) {
                    connection.close();
                }
            } catch (IOException | TimeoutException e) {
                e.printStackTrace();
            }
        }
    }
}
