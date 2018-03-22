package yingdg.exercise.redis;

import redis.clients.jedis.Jedis;

/**
 * Created by yingdg on 2017/8/30.
 */
public class RedisReplication {
    private static Jedis jedis = new Jedis();

    /*
    Redis主从复制
     */
    public static void main(String[] args) {
        // master
        String slaveofNoOne = jedis.slaveofNoOne();
        System.out.println(slaveofNoOne); // OK

        // slave
        String slaveof = jedis.slaveof("localhost", 6379); // 自己作为自己的slave会引起死循环复制问题
        System.out.println(slaveof);
        String info = jedis.info("Replication");
        System.out.println(info);

        // 数据同步命令
        jedis.sync();

        jedis.close();
    }
}
