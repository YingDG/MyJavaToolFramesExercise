package yingdg.exercise.redis.datatype;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.Set;

/**
 * Created by yingdg on 2017/8/9.
 */
public class RedisSortedSets {
    private static Jedis jedis = new Jedis();

    /*
    有序集合，又称zset

    底层实现为skip list和hash table的混合
     */
    public static void main(String[] args) {
        // 添加
        Long zadd1 = jedis.zadd("myzset", 1.0, "m1");
        System.out.println(zadd1); // 1
        Long zadd2 = jedis.zadd("myzset", 2.0, "m2");
        System.out.println(zadd2); // 1
        Long zadd3 = jedis.zadd("myzset", 3.0, "m3");
        System.out.println(zadd3); // 1

        // 获取元素排名后下标
        Long zrank = jedis.zrank("myzset", "m1"); // 从小到大
        System.out.println(zrank); // 0
        Long zrevrank = jedis.zrevrank("myzset", "m1"); // 从大到小
        System.out.println(zrevrank); // 2

        // 增加score
        Double zincrby = jedis.zincrby("myzset", 2.0, "m3");
        System.out.println(zincrby); // 5.0（最终score）

        // 元素个数
        Long zcard = jedis.zcard("myzset");
        System.out.println(zcard); // 3

        // 元素socre
        Double zscore = jedis.zscore("myzset", "m3");
        System.out.println(zscore); // 5.0

        // 删除
        Long zrem = jedis.zrem("myzset", "m2");
        System.out.println(zrem); // 1

        // 查询所有
        Set<String> zrange = jedis.zrange("myzset", 0, -1); // 从小到大
        System.out.println(zrange); // [m1, m3]
        Set<String> zrevrange = jedis.zrevrange("myzset", 0, -1); // 从大到小
        System.out.println(zrevrange); // [m3, m1]

        // 确定score区间数量
        Long zcount = jedis.zcount("myzset", 0.5, 1.5);
        System.out.println(zcount); // 1

        // score查询member
        Set<String> myzset1 = jedis.zrangeByScore("myzset", 0.5, 1.5);
        System.out.println(myzset1); // [m1]
        // score查询member+score
        Set<Tuple> myzset2 = jedis.zrangeByScoreWithScores("myzset", 2.5, 9.5);
        System.out.println(myzset2.getClass().getName()); // java.util.LinkedHashSet
        for (Tuple tuple : myzset2) {
            System.out.println(tuple.getElement() + ":" + tuple.getScore()); // m3:3.0
        }

        // 删除集合中排名在给定区间的元素
        Long zremrangeByRank = jedis.zremrangeByRank("myzset", 1, 2);
        System.out.println(zremrangeByRank); // 1
        // 删除集合中score 在给定区间的元素
        Long zremrangeByScore = jedis.zremrangeByScore("myzset", 0.5, 2.0);
        System.out.println(zremrangeByScore); // 1

        jedis.flushDB();
        jedis.close();
    }
}
