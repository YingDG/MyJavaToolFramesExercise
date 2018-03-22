package yingdg.exercise.redis.datatype;

import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by yingdg on 2017/8/9.
 */
public class RedisHashes {
    private static Jedis jedis = new Jedis("localhost", 6379);

    /*
    散列，类似于Map结构（key-Map）

    添加、删除操作都是O(1)，适合存储对象
     */
    public static void main(String[] args) {
        // 添加
        Long hset1 = jedis.hset("myhash", "mykey1", "a");
        System.out.println(hset1); // 1
        Long hset2 = jedis.hset("myhash", "mykey2", "b");
        System.out.println(hset2); // 1
        Long hset3 = jedis.hset("myhash", "mykey3", "c");
        System.out.println(hset3); // 1
        Long hsetnx = jedis.hsetnx("myhash", "mykey3", "d");
        System.out.println(hsetnx); // 0

        // 一次添加多个值
        Map<String, String> values = new HashMap<>();
        values.put("key1", "1");
        values.put("key2", "2");
        values.put("key3", "3");
        String hmset = jedis.hmset("myhash2", values);
        System.out.println(hmset); // OK

        // key是否存在
        Boolean hexists = jedis.hexists("myhash2", "key3");
        System.out.println(hexists); // true
        // 获取hash元素个数
        Long hlen = jedis.hlen("myhash2");
        System.out.println(hlen); // 3
        // 获取所有key
        Set<String> hkeys = jedis.hkeys("myhash2");
        System.out.println(hkeys); // [key1, key2, key3]
        // 获取所有values
        List<String> hvals = jedis.hvals("myhash2");
        System.out.println(hvals); // [1, 2, 3]（无序）
        // 获取所有key-values
        Map<String, String> hgetAll = jedis.hgetAll("myhash2");
        System.out.println(hgetAll); // {key1=1, key2=2, key3=3}

        // 删除
        Long hdel = jedis.hdel("myhash", "mykey1", "mykey2");
        System.out.println(hdel); // 2

        // 获取当前所有
        Map<String, String> myhash = jedis.hgetAll("myhash");
        System.out.println(myhash); // {mykey3=c}
        // 获取特定key-value
        String hget = jedis.hget("myhash", "mykey3");
        System.out.println(hget); // c

        jedis.flushDB();
        jedis.close();
    }
}
