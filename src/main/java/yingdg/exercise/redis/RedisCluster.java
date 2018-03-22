package yingdg.exercise.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yingdg on 2017/8/30.
 */
public class RedisCluster {
    private static Jedis jedis = new Jedis();

    /*
    Redis集群
     */
    public static void main(String[] args) {
        /*
        配置集群信息
         */
        JedisShardInfo jedisShardInfo = new JedisShardInfo("localhost");
        jedisShardInfo.setConnectionTimeout(5);

        List<JedisShardInfo> jedisShardInfoList = new ArrayList<>();
        jedisShardInfoList.add(jedisShardInfo);

        /*
        获取连接池配置
         */
        JedisPoolConfig jedisPoolConfig = MyJedisPool.getJedisPoolConfig();

        /*
        初始化集群jedis对象，需要配置文件开启集群功能：cluster-enabled yes
         */
        ShardedJedisPool shardedJedisPool = new ShardedJedisPool(jedisPoolConfig, jedisShardInfoList);

        /*
        操作实例：切片客户端
         */
        try (ShardedJedis shardedJedis = shardedJedisPool.getResource()) {
            /*
            相关redis操作
             */

            String echo = shardedJedis.echo("hello");
            System.out.println(echo);
        } catch (Exception e) {
            e.printStackTrace();
        }

        shardedJedisPool.close();
    }
}
