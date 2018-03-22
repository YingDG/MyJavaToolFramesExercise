package yingdg.exercise.redis.datatype;

import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * Created by yingdg on 2017/8/9.
 */
public class RedisStrings {
    private static Jedis jedis = new Jedis("localhost", 6379);

    /*
    字符串
     */
    public static void main(String[] args) {
        // 赋值
        String set = jedis.set("hello", "hello");
        System.out.println(set); // OK
        Long setrange = jedis.setrange("hello", 2, "123"); // 从字符串下标2开始替换value
        System.out.println(setrange); // 5（现在字符串长度）
        Long setnx = jedis.setnx("hello", "hello"); // key存在不赋值
        System.out.println(setnx); // 0
        String setex = jedis.setex("hello", 2, "hello"); // 同时设置有效期
        System.out.println(setex); // OK

        // 设置有效期
        Long expire = jedis.expire("hello", 50);
        System.out.println(expire); // 1（受影响结果数）

        // 获取
        String hello = jedis.get("hello");
        System.out.println(hello);
        String getrange = jedis.getrange("hello", 2, -1); // 从下标2取到底
        System.out.println(getrange); // llo
        String getrange1 = jedis.getrange("hello", -2, -1); // 从右边数下标取到底
        System.out.println(getrange1);

        // 删除
        Long del = jedis.del("hello");
        System.out.println(del); // 1（受影响结果数）

        // 多赋值
        String mset = jedis.mset("a", "a", "b", "b", "c", "c"); // key-value对
        System.out.println(mset); // OK
        Long msetnx = jedis.msetnx("a", "aaa", "d", "d"); // 返回0时所有操作回滚
        System.out.println(msetnx); // 0

        // 多取值
        List<String> mget = jedis.mget("a", "b", "c");
        System.out.println(mget);

        // 返加旧值并同时赋新值
        String getSet = jedis.getSet("a", "aa");
        System.out.println(getSet); // a

        // 字符长度
        Long strlen = jedis.strlen("a");
        System.out.println(strlen); // 2

        jedis.set("one", "1");
        // 加1
        Long incr = jedis.incr("one");
        System.out.println(incr); // 2（结果）
        // 加指定数值
        Long incrBy = jedis.incrBy("one", 5);
        System.out.println(incrBy); // 7（结果）
        // 减1
        Long decr = jedis.decr("one");
        System.out.println(decr); // 6
        // 减指定数值
        Long decrBy = jedis.decrBy("one", 3);
        System.out.println(decrBy); // 3

        jedis.flushDB();
        jedis.close();
    }
}
