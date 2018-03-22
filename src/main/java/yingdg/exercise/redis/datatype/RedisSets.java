package yingdg.exercise.redis.datatype;

import redis.clients.jedis.Jedis;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by yingdg on 2017/8/9.
 */
public class RedisSets {
    private static Jedis jedis = new Jedis("localhost");

    /*
    无序集合

    底层为hash table实现，可以进行集合操作
     */
    public static void main(String[] args) {
        // 添加元素
        Long sadd = jedis.sadd("myset", "a", "b", "c");
        System.out.println(sadd);  // 3

        // 移除元素
        Long srem = jedis.srem("myset", "b");
        System.out.println(srem); // 1

        // 查询元素是否存在
        Boolean sismember = jedis.sismember("myset", "b");
        System.out.println(sismember); // false

        // 获取所有元素
        Set<String> myset = jedis.smembers("myset");
        System.out.println(myset.getClass().getName()); // redis.clients.jedis.BinaryJedis$SetFromList
        System.out.println(new HashSet<>(myset)); // a,c
        // 随机返回元素
        String srandmember = jedis.srandmember("myset"); // 返回一个元素
        System.out.println(srandmember);
        List<String> srandmember1 = jedis.srandmember("myset", 2); // 返回指定个数元素
        System.out.println(srandmember1);

        // 元素个数
        Long scard = jedis.scard("myset");
        System.out.println(scard); // 2

        // 弹出（获取并删除）一个元素
        String spop = jedis.spop("myset");
        System.out.println(spop); // c

        // 差集
        jedis.sadd("myset2", "1", "a", "c");
        Set<String> sdiff = jedis.sdiff("myset2", "myset"); // 以myset为基点比较
        System.out.println(sdiff); // [1, c]
        Long sdiffstore = jedis.sdiffstore("myset3", "myset2", "myset"); // 差集存至新set
        System.out.println(sdiffstore); // 2（差集元素个数）
        // 交集
        Set<String> sinter = jedis.sinter("myset", "myset2");
        System.out.println(sinter); // [c]
        Long sinterstore = jedis.sinterstore("myset4", "myset2", "myset");
        System.out.println(sinterstore); // 1（差集元素个数）
        // 并集
        Set<String> sunion = jedis.sunion("myset2", "myset");
        System.out.println(sunion); // [c, 1, a]
        Long sunionstore = jedis.sunionstore("myset5", "myset2", "myset");
        System.out.println(sunionstore); // 3（差集元素个数）

        // 指定元素移动到另一个set中
        Long smove = jedis.smove("myset2", "myset", "1");
        System.out.println(smove); // 1

        jedis.flushDB();
        jedis.close();
    }
}
