package yingdg.exercise.redis.command;

import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * Created by yingdg on 2017/8/29.
 */
public class RedisServerCommands {
    private static Jedis jedis = new Jedis();

    /*
    Jedis Server常用命令
     */
    public static void main(String[] args) {
        // 连接是否存活
        String ping = jedis.ping();
        System.out.println(ping); // PONG

        // 命令行打印
        String echo = RedisServerCommands.jedis.echo("jedis");
        System.out.println(echo); // jedis

        // 选择db节点
        String select = jedis.select(1);
        System.out.println(select); // OK

        // 当前数据库中key 的数目
        Long dbSize = jedis.dbSize();
        System.out.println(dbSize);

        // 服务器的信息和统计
        String info = jedis.info();
        System.out.println(info); // 包括Server, Clients, Memory, Persistence, Stats, Replication, CPU, Cluster, Keyspace
        System.out.println(jedis.info("Server")); // 只显示Server部分

        // 实时转储收到的请求（线程阻塞）
//        jedis.monitor(new JedisMonitor() {
//            @Override
//            public void onCommand(String command) {
//                System.out.println(command);
//            }
//        });

        // 服务器配置信息
        List<String> configGet = jedis.configGet("*");
        System.out.println(configGet); // 所有配置信息
        // String configSet = jedis.configSet("bind", "localhost");
        // System.out.println(configSet);

        // 删除key
        String flushDB = jedis.flushDB();// 当前db节点
        System.out.println(flushDB); // OK
        String flushAll = jedis.flushAll();// 所有db节点
        System.out.println(flushAll); // OK

        // 退出连接
        String quit = jedis.quit();
        System.out.println(quit); // OK
    }
}
