package yingdg.exercise.redis;

import redis.clients.jedis.Jedis;

/**
 * Created by yingdg on 2017/8/9.
 */
public class HelloRedis {
    /*
    数据类型：
    Strings, Lists, Sets, Hashes, Sorted Sets, BitMaps, HyperLogLogs

    命令行开启Server：redis-server redis.conf
    命令行开启Client：redis-cli -h ip -p port
     */
    public static void main(String[] args) {
        Jedis jedis = new Jedis("localhost", 6379);
        /*
        Jedis非线程安全
         */

        // 赋值
        String set = jedis.set("hello", "hello");
        System.out.println(set); // OK
        // 获取
        String hello = jedis.get("hello");
        System.out.println(hello);
        // 删除
        Long del = jedis.del("hello");
        System.out.println(del); // 1

        jedis.flushDB();
        jedis.close();
    }
}
