package yingdg.exercise.redis.pubandsub;

import redis.clients.jedis.Jedis;

/**
 * Created by yingdg on 2017/8/28.
 */
public class RedisListener2 {
    private static Jedis jedis = new Jedis();

    /*
    信息消费者
     */
    public static void main(String[] args) {
        // 订阅并接收消息
        // jedis.subscribe(new MySubscriber(), "hello", "hello2"); // 启动了订阅监听，线程将在这里被阻塞
        jedis.psubscribe(new MySubscriber(), "hello*"); // 按表达式订阅
        /*
        订阅得到的信息在lister的onPMessage(...)方法中进行处理
         */
    }
}
