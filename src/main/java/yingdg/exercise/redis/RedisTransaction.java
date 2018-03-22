package yingdg.exercise.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import java.io.IOException;
import java.util.List;

/**
 * Created by yingdg on 2017/8/30.
 */
public class RedisTransaction {
    private static Jedis jedis = new Jedis();

    /*
    所有Redis操作均为单线程

    Redis事务目前只回滚异常操作

    指令：WATCH, MULTI, EXEC, UNWATCH, DISCARD

    WATCH：开启乐观锁，一般参数为key

    MULTI：开启事务（上下文）

    DISCARD：关闭事务，所有操作回滚

    EXEC：提交事务

    UNWATCH：关闭乐观锁
     */
    public static void main(String[] args) throws IOException {
        jedis.set("redis", "redis");

        // 开启乐观锁，防止脏数据操作
        String watch = jedis.watch("redis");
        System.out.println(watch); // OK

        // 开启事务
        Transaction transaction = jedis.multi();

//        Response<String> watch1 = transaction.watch("redis");
//        System.out.println(watch1); // Response string

        // 改值
        transaction.set("redis", "sider"); // 事务中所有操作交由Transaction对象

        // 取消事务
//        String discard = multi.discard();
//        System.out.println(discard); // OK
        // 提交事务
        List<Object> exec = transaction.exec();
        System.out.println(exec); // [OK]
//        List<Response<?>> execGetResponse = multi.execGetResponse();
//        System.out.println(execGetResponse); // [Response string]
        // 清除事务
        transaction.clear();
        // 关闭事务
        transaction.close();

        // 关闭乐观锁
        String unwatch = jedis.unwatch();
        System.out.println(unwatch); // OK

        jedis.flushDB();
        jedis.close();
    }
}
