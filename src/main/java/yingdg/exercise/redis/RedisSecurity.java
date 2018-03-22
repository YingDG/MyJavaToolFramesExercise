package yingdg.exercise.redis;

import redis.clients.jedis.Jedis;

/**
 * Created by yingdg on 2017/8/30.
 */
public class RedisSecurity {
    private static Jedis jedis = new Jedis();

    public static void main(String[] args) {
        // 密码验证
        String auth = jedis.auth("123456");
        System.out.println(auth); // OK
    }
}
