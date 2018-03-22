package yingdg.exercise.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;

import java.io.IOException;

/**
 * Created by yingdg on 2017/8/30.
 */
public class RedisPipeline {
    private static Jedis jedis = new Jedis();

    /*
    Redis批处理
     */
    public static void main(String[] args) throws IOException {
        // 开启批处理
        Pipeline pipelined = jedis.pipelined();

        /*
        此后所有操作交给Pipeline对象
         */

        // 开启事务
        Response<String> multi = pipelined.multi();
        System.out.println(multi); // Response string
        // 是否开启事务
        boolean inMulti = pipelined.isInMulti();
        System.out.println(inMulti); // true

        /*
        此时事务操作对象仍为Pipeline
         */

        pipelined.flushDB();
        // 关闭
        pipelined.close();
    }
}
