package yingdg.exercise.redis.pubandsub;

import redis.clients.jedis.Jedis;

/**
 * Created by yingdg on 2017/8/28.
 */
public class RedisPublisher {
    private static Jedis jedis = new Jedis();

    /*
    信息生产者
     */
    public static void main(String[] args) {
        // 发布消息
        jedis.publish("hello1", "hello,redis!");
        jedis.publish("hello2", "hello!");
    }
}
