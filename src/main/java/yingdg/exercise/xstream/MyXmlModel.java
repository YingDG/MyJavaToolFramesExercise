package yingdg.exercise.xstream;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import com.thoughtworks.xstream.converters.basic.BooleanConverter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by zdm on 2018/3/20.
 */
// 类别名
@XStreamAlias("MyXmlModel")
public class MyXmlModel {
    // 设为属性值
    @XStreamAsAttribute
    private int id;
    // 别名
    @XStreamAlias("username")
    private String name;
    private String name2;
    // 忽略该属性
//    @XStreamOmitField
    private Date date;
    // list节点序列化
    @XStreamImplicit(itemFieldName = "value")
    private List<String> list = new ArrayList<>();
    // 转换器
    @XStreamConverter(value = BooleanConverter.class, booleans = {false}, strings = {"男", "女"})
    private boolean sex = true; // 男

    public MyXmlModel() {
    }

    public MyXmlModel(int id, String name, Date date) {
        this.id = id;
        this.name = name;
        this.date = date;

        list.add("a");
        list.add("b");
        list.add("c");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName2() {
        return name2;
    }

    public MyXmlModel setName2(String name2) {
        this.name2 = name2;
        return this;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "MyXmlModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", list=" + list +
                '}';
    }
}
