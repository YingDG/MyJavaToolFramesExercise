package yingdg.exercise.redis.command;

import redis.clients.jedis.Jedis;

import java.util.Set;

/**
 * Created by yingdg on 2017/8/29.
 */
public class RedisKeyCommands {
    private static Jedis jedis = new Jedis();

    /*
    Redis Key常用命令
     */
    public static void main(String[] args) {
        jedis.set("hello", "hello");

        // 获取数据类型
        String type = jedis.type("hello");
        System.out.println(type); // string

        // 设置过期时间
        Long expire = jedis.expire("hello", 10);
        System.out.println(expire); // 1
        // 查看过期时间
        Long ttl = jedis.ttl("hello");
        System.out.println(ttl); // 10
        Long pttl = jedis.pttl("hello"); // 毫秒查看
        System.out.println(pttl); // 9999
        // 移除过期时间
        Long persist = jedis.persist("hello");
        System.out.println(persist); // 1

        // 根据regex列出所有key
        Set<String> keys = jedis.keys("*");
        System.out.println(keys); // [hello]

        // 获取随机key
        String randomKey = jedis.randomKey();
        System.out.println(randomKey);

        // 验证是否存在key
        Boolean exists = jedis.exists("hello");
        System.out.println(exists); // true

        // 移动数据至其他db节点
        String select = jedis.select(0);
        System.out.println(select); // OK
        Long move = jedis.move("hello", 1); // 移至db_1
        System.out.println(move); // 1
        System.out.println(jedis.get("hello")); // null
        jedis.select(1);
        System.out.println(jedis.get("hello")); // hello

        // 重命名key
        String rename = jedis.rename("hello", "hello2");
        System.out.println(rename); // OK

        jedis.flushAll();
        jedis.close();
    }
}
