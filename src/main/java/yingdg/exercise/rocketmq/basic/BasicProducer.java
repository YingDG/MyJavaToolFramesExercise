package yingdg.exercise.rocketmq.basic;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.io.UnsupportedEncodingException;

/**
 * Created by yingdg on 2018/3/3.
 */
public class BasicProducer {
    public static void main(String[] args) throws MQClientException, InterruptedException {
        // 声明并初始化一个producer
        // 需要一个producer group名字作为构造方法的参数，这里为producer1
        DefaultMQProducer producer = new DefaultMQProducer("producer1");

        // 设置NameServer地址,此处应改为实际NameServer地址，多个地址之间用；分隔
        // NameServer的地址必须有，但是也可以通过环境变量的方式设置，不一定非得写死在代码里
        producer.setNamesrvAddr("10.1.54.121:9876;10.1.54.122:9876");
        // 调用start()方法启动一个producer实例
        producer.start();
        producer.setRetryTimesWhenSendAsyncFailed(0);

        // 发送10条消息到Topic为TopicTest，tag为TagA，消息内容为“Hello RocketMQ”拼接上i的值
        for (int i = 0; i < 10; i++) {
            try {
                Message msg = new Message(
                        "TopicTest",
                        "TagA",
                        ("Hello RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET));

                // 调用producer的send()方法发送消息
                // 同步响应处理
                SendResult sendResult = producer.send(msg);
                // 打印返回结果，可以看到消息发送的状态以及一些相关信息
                System.out.println(sendResult);

                // 异步响应处理
                /*
                final int index = i;
                producer.send(msg, new SendCallback() {
                    @Override
                    public void onSuccess(SendResult sendResult) {
                        System.out.printf("%-10d OK %s %n", index, sendResult.getMsgId());
                    }

                    @Override
                    public void onException(Throwable e) {
                        System.out.printf("%-10d Exception %s %n", index, e);
                        e.printStackTrace();
                    }
                });
                */

                // 无响应发送
                // producer.sendOneway(msg);
            } catch (UnsupportedEncodingException | RemotingException | MQBrokerException e) {
                e.printStackTrace();
                Thread.sleep(1000);
            }
        }

        //发送完消息之后，调用shutdown()方法关闭producer
        producer.shutdown();
    }
}
