package yingdg.exercise.jackson.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.util.Date;
import java.util.HashMap;

/**
 * Created by yingdg on 2017/6/8.
 */
/*
开启/禁止自动检测
fieldVisibility:字段的可见级别
    ANY:任何级别的字段都可以自动识别
    NONE:所有字段都不可以自动识别
    NON_PRIVATE:非private修饰的字段可以自动识别
    PROTECTED_AND_PUBLIC:被protected和public修饰的字段可以被自动识别
    PUBLIC_ONLY:只有被public修饰的字段才可以被自动识别
    DEFAULT:同PUBLIC_ONLY

jackson默认的字段属性发现规则如下：
所有被public修饰的字段->所有被public修饰的getter->所有被public修饰的setter

除了fieldVisibility可以设置外，还可以设置getterVisibility、setterVisibility、isGetterVisibility、creatorVisibility级别
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.PUBLIC_ONLY)
// @JsonNaming(PropertyNamingStrategy.LowerCaseStrategy.class) // 制定统一的命名规则，可自定义
// @JsonFilter("myFilter") // 过滤器
public class Role {
    // @JacksonInject("rolename") // 作用于属性、方法、构造参数上，被用来反序列化时标记已经被注入的属性
    private String rolename;
    // @JsonSerialize(using = MySerializer.class) // 自定义字段序列化
    // @JsonDeserialize(using = MyDeserializer.class) // 自定义字段反序列化
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm") // 日期格式
    private Date date;
    // @JsonIgnore // 完全忽略这个属性
    /*
    用来在序列化/反序列化时为该对象或字段添加一个对象识别码，通常是用来解决循环嵌套的问题
    配置属性generator来确定识别码生成的方式，有简单的，配置属性property来确定识别码的名称(默认@id)，识别码名称没有限制。
    对象识别码可以是虚拟的，即存在在JSON中，但不是POJO的一部分，也可以是真实存在的，即以对象的属性为识别码
     */
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    private User user;

    /*
    标注构造方法或静态工厂方法上，使用该方法来构建实例，
    默认的是使用无参的构造方法，通常是和@JsonProperty或@JacksonInject配合使用
    */
    @JsonCreator
    public Role(@JsonProperty("rolename") String rolename, @JsonProperty("date") Date date) {
        this.rolename = rolename;
        this.date = date;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Role{" +
                "rolename='" + rolename + '\'' +
                ", date=" + date +
                '}';
    }

    // @JsonAnyGetter // 序列化时用来处理遇到未知的属性的时候调用
    public Object get() {
        return null;
    }

    // @JsonAnySetter // 反序列化时用来处理遇到未知的属性的时候调用
    public void set(String name, Object value) {
        new HashMap<>().put(name, value);
    }

    @JsonIgnoreType // 忽略序列化数据类型
    public static class Sub1 extends Role {
        private String name;

        public Sub1(@JsonProperty String rolename, @JsonProperty Date date) {
            super(rolename, date);
        }

        //getters、setters省略
    }

}
