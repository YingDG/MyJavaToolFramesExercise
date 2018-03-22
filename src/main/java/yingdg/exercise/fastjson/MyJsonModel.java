package yingdg.exercise.fastjson;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

/**
 * Created by zdm on 2018/3/20.
 */
public class MyJsonModel {
    private int id;
    @JSONField(name = "username")
    private String name;
    private Date date;

    public MyJsonModel() {
    }

    public MyJsonModel(int id, String name, Date date) {
        this.id = id;
        this.name = name;
        this.date = date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @JSONField(name = "ID")
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
