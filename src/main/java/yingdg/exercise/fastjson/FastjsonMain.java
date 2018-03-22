package yingdg.exercise.fastjson;

import com.alibaba.fastjson.JSON;

import java.util.Date;

/**
 * Created by zdm on 2018/3/20.
 */
public class FastjsonMain {
    public static void main(String[] args) {
        // to json
        String json = JSON.toJSONString(new MyJsonModel(1, "json", new Date())); // 调用对象getters
        System.out.println(json);

        // from json
        MyJsonModel model = JSON.parseObject(json, MyJsonModel.class); // 调用对象setters，否则调用先无参后有参构造方法
        System.out.println(model);
    }
}
