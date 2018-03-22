package yingdg.exercise.redis.datatype;

import redis.clients.jedis.BinaryClient;
import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * Created by yingdg on 2017/8/9.
 */
public class RedisLists {
    private static Jedis jedis = new Jedis("localhost");

    /*
    列表

    可以当作栈、队列（堆）使用，结构是每个元素为String类型的双向链表
     */
    public static void main(String[] args) {
        // 添加元素
        Long rpush = jedis.rpush("mylist", "a", "b", "c"); // 尾部添加
        System.out.println(rpush); // 3（结果数）
        Long lpush = jedis.lpush("mylist", "1", "2"); // 头部添加
        System.out.println(lpush); // 5（结果数）

        // 指定位置插入
        Long linsert = jedis.linsert("mylist", BinaryClient.LIST_POSITION.BEFORE, "a", "4");
        System.out.println(linsert); // 6（最终结果数）

        // 指定下标更换元素
        String lset = jedis.lset("mylist", 1, "3");
        System.out.println(lset); // OK

        // 取值
        List<String> mylist = jedis.lrange("mylist", 0, -1); // 顺序取值
        System.out.println(mylist); // [2, 3, 4, a, b, c]

        // 移除元素
        String lpop = jedis.lpop("mylist"); // 从头部取值并移除
        System.out.println(lpop); // 2（弹出元素）
        String rpop = jedis.rpop("mylist");// 从尾部取值并移除
        System.out.println(rpop); // c

        // 指定下标取值
        String lindex = jedis.lindex("mylist", 1); //
        System.out.println(lindex); // 4（下标从0开始）

        // 删除指定个数元素
        Long lrem = jedis.lrem("mylist", 2, "b");
        System.out.println(lrem); // 1
        jedis.lrem("mylist", 0, ""); // 删除全部元素

        // 保留下标元素
        String ltrim = jedis.ltrim("mylist", 1, 3);
        System.out.println(ltrim); // OK

        // 长度
        Long llen = jedis.llen("mylist");
        System.out.println(llen); // 2

        // 取尾补头
        String rpoplpush = jedis.rpoplpush("mylist", "mylist2"); // 原子操作
        System.out.println(rpoplpush); // a（当前尾部元素）

        jedis.flushDB();
        jedis.close();
    }
}
