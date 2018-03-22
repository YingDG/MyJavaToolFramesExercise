package yingdg.exercise.redis;

import redis.clients.jedis.Jedis;

/**
 * Created by yingdg on 2017/8/30.
 */
public class RedisPersistence {
    private static Jedis jedis = new Jedis();

    /*
    Redis数据持久化

    支持两种持久化方式：
    Snapshotting（快照）：默认方式；
    Append-only file（缩写aof）：追加方式

    父进程继续处理client 请求，子进程负责将内存内容写入到临时文件
     */
    public static void main(String[] args) {
        // 主线程保存快照，会阻塞所有客户端请求
        String save = jedis.save();
        System.out.println(save); // OK
        // 子线程保存快照
        String bgsave = jedis.bgsave();
        System.out.println(bgsave); // Background saving started

        // 压缩aof 的持久化文件
        String bgrewriteaof = jedis.bgrewriteaof();
        System.out.println(bgrewriteaof); // Background append only file rewriting scheduled

        jedis.flushDB();
        jedis.close();
    }
}
