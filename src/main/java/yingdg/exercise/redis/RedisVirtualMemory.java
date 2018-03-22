package yingdg.exercise.redis;

/**
 * Created by yingdg on 2017/8/30.
 */
public abstract class RedisVirtualMemory {
    /*
    Redis虚拟内存

    vm-enabled yes #开启vm 功能
    vm-swap-file /tmp/redis.swap #交换出来的value 保存的文件路径
    vm-max-memory 1000000 #redis 使用的最大内存上限
    vm-page-size 32 #每个页面的大小32 个字节
    vm-pages 134217728 #最多使用多少页面
    vm-max-threads 4 #用于执行value 对象换入换出的工作线程数量
     */
}
