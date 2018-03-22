package yingdg.exercise.jackson.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.annotation.JsonView;
import yingdg.exercise.jackson.JsonViewFilter;

/**
 * Created by yingdg on 2017/6/8.
 */
@JsonPropertyOrder(alphabetic = false, value = {"age,id"}) // 序列化时需要对属性做排序
@JsonIgnoreProperties(ignoreUnknown = true, value = {""}) // 忽略掉Pojo中指定的属性
@JsonRootName("USER") // 自定义根元素名称
public class User {
    private Integer id;
    @JsonProperty(value = "Name", defaultValue = "") // 改变某个成员属性
    @JsonView(JsonViewFilter.OutputA.class) // 视图模板输出
    private String username;
    private int age;
    @JsonUnwrapped(prefix = "", suffix = "") // JSON对象的属性添加到解闭到上级JSON对象
    private Role role;

    public User() {
    }

    public User(Integer id, String username, int age) {
        this.id = id;
        this.username = username;
        this.age = age;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", age=" + age +
                ", role=" + role +
                '}';
    }
}
