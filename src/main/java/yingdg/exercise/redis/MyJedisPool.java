package yingdg.exercise.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by yingdg on 2017/8/9.
 */
public class MyJedisPool {
    /*
    连接池配置
     */
    public static JedisPoolConfig getJedisPoolConfig() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        // 如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽), 默认8个
        jedisPoolConfig.setMaxTotal(10);
        // 控制一个pool最多有多少个状态为idle(空闲的)的jedis实例, 默认8个
        jedisPoolConfig.setMaxIdle(8);
        // borrow(引入)一个jedis实例时，最大的等待时间，如果超过等待时间，则直接抛出JedisConnectionException, 默认-1
        jedisPoolConfig.setMaxWaitMillis(3000);
        // borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的, 默认false
        jedisPoolConfig.setTestOnBorrow(true);
        //连接耗尽时是否阻塞, false报异常,ture阻塞直到超时, 默认true
        jedisPoolConfig.setBlockWhenExhausted(true);
        //设置的逐出策略类名, 默认DefaultEvictionPolicy(当连接超过最大空闲时间,或连接数超过最大空闲连接数)
        jedisPoolConfig.setEvictionPolicyClassName("org.apache.commons.pool2.impl.DefaultEvictionPolicy");
        //是否启用pool的jmx管理功能, 默认true
        jedisPoolConfig.setJmxEnabled(true);
        // TODO，默认为"pool"
        jedisPoolConfig.setJmxNamePrefix("pool");
        //是否启用后进先出, 默认true
        jedisPoolConfig.setLifo(true);
        //逐出连接的最小空闲时间 默认1800000毫秒(30分钟)
        jedisPoolConfig.setMinEvictableIdleTimeMillis(1800000);
        //最小空闲连接数, 默认0
        jedisPoolConfig.setMinIdle(0);
        //每次逐出检查时 逐出的最大数目 如果为负数就是 : 1/abs(n), 默认3
        jedisPoolConfig.setNumTestsPerEvictionRun(3);
        //对象空闲多久后逐出, 当空闲时间>该值 且 空闲连接>最大空闲数 时直接逐出,不再根据MinEvictableIdleTimeMillis判断  (默认逐出策略)
        jedisPoolConfig.setSoftMinEvictableIdleTimeMillis(1800000);
        //在空闲时检查有效性, 默认false
        jedisPoolConfig.setTestWhileIdle(false);
        //逐出扫描的时间间隔(毫秒) 如果为负数,则不运行逐出线程, 默认-1
        jedisPoolConfig.setTimeBetweenEvictionRunsMillis(-1);

        return jedisPoolConfig;
    }

    /*
    Redis连接池
     */
    public static void main(String[] args) {
        JedisPoolConfig jedisPoolConfig = getJedisPoolConfig();

        JedisPool jedisPool = new JedisPool(
                jedisPoolConfig, "localhost", 6379, 0, "123456");

        try (Jedis jedis = jedisPool.getResource()) {
            jedis.select(1);

            jedis.set("pool", "pool");
            jedis.expire("pool", 60);
            String get = jedis.get("pool");
            System.out.println(get);

            jedis.flushDB();
        } catch (Exception e) {
            e.printStackTrace();
        }

        jedisPool.close();
    }
}
