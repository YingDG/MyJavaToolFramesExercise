package yingdg.exercise.lombok;

import lombok.AllArgsConstructor;
import lombok.Cleanup;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.Synchronized;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.experimental.Delegate;
import lombok.experimental.Wither;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yingdg on 2017/4/19.
 */
// 生成hashCode和equal方法
@EqualsAndHashCode
// 生成toString方法
@ToString
// 无参构造
@NoArgsConstructor
// 全参构造
@AllArgsConstructor
// 带参构造
@RequiredArgsConstructor(
        // 和@NonNull配合指定对象静态实例方法名
        staticName = "of")
/*
@Accessors(
        // 是否带get/set
        chain = true,
        // 是否为链式set
        fluent = true,
        // get/set方法去掉通用前缀
        prefix = ""
)
*/
public class Model2 {
    @Getter
    @Setter
    @NonNull
    private int id;
    @Getter
    @Setter
    private String mname;
    @Getter
    private transient Integer price = 10;

    // 同步加锁
    private final Object lock = new Object();

    @Synchronized
    public void hello() {
        System.out.println("hello");
    }

    @Synchronized("lock")
    public void hello2() {
        System.out.println("hello2");
    }

    // final变量赋值
//    @Wither
    @Getter(lazy = true)
    private final int f = 0;

    // 生成集合容器
//     @Delegate
//     private List<String> list = new ArrayList<>();

    // 自动关闭流对象
//    @Cleanup

}
