package yingdg.exercise.redis.datatype;

import redis.clients.jedis.Jedis;

/**
 * Created by yingdg on 2017/8/9.
 */
public class RedisBitMaps {
    private static Jedis jedis = new Jedis();

    /*
    BitMap

    bit位存储
     */
    public static void main(String[] args) {
        // 设置某个bit位上的值
        Boolean setbit = jedis.setbit("bit", 10, "1");
        System.out.println(setbit); // false

        // 验证某个bit位上是否有值
        Boolean getbit = jedis.getbit("bit", 10);
        System.out.println(getbit); // true
        System.out.println(jedis.getbit("bit", 9)); // false

        // 查看占了几个位？
        Long bit = jedis.bitcount("bit");
        System.out.println(bit); // 1

        // 有值的位置？
        Long bitpos = jedis.bitpos("bit", true);
        System.out.println(bitpos); // 10

        jedis.flushDB();
        jedis.close();
    }
}
