package yingdg.exercise.redis.datatype;

import redis.clients.jedis.Jedis;

/**
 * Created by yingdg on 2017/8/9.
 */
public class RedisHyperLogLogs {
    private static Jedis jedis = new Jedis();

    /*
    HyperLogLog

    使输入元素的数量或者体积非常非常大，计算基数所需的空间总是固定的、并且是很小的
    只会根据输入元素来计算基数，而不会储存输入元素本身，所以不能返回输入的各个元素
    统计相同数量的元素，所需的内存要比集合list少得多
     */
    public static void main(String[] args) {
        Long pfadd = jedis.pfadd("ip", "localhost");
        System.out.println(pfadd); // 1

        long pfcount = jedis.pfcount("ip");
        System.out.println(pfcount); // 1

        String pfmerge = jedis.pfmerge("ip", "net");
        System.out.println(pfmerge); // OK

        jedis.flushDB();
        jedis.close();
    }
}
