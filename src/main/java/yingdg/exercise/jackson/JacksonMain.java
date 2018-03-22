package yingdg.exercise.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import yingdg.exercise.jackson.model.Role;
import yingdg.exercise.jackson.model.User;

import java.io.IOException;
import java.util.Date;

/**
 * Created by yingdg on 2017/6/8.
 */
public class JacksonMain {
    public static void main(String[] args) throws JsonProcessingException {
        User user = initUser();
        Role role = initRole();
        user.setRole(role);
        role.setUser(user);
        System.out.println(user);
        System.out.println(role);

        String userStr = JacksonUtil.toJson(user);
        System.out.println(userStr);
        String roleStr = JacksonUtil.toJson(role);
        System.out.println(roleStr);

        User user1 = JacksonUtil.toObject(userStr, User.class);
        System.out.println(user1);
        Role role1 = JacksonUtil.toObject(roleStr, Role.class);
        System.out.println(role1);

        // 视图输出
        ObjectMapper mapper = new ObjectMapper();
        /*
        true将输出没有JsonView注解的属性，false关闭将输出有JsonView注解的属性
        与@JsonView配合使用
         */
        mapper.configure(MapperFeature.DEFAULT_VIEW_INCLUSION, false);
        /*
        过滤掉不想要的属性
        与@JsonFilter配合使用
         */
        FilterProvider filters = new SimpleFilterProvider().addFilter("myFilter", SimpleBeanPropertyFilter.filterOutAllExcept("a"));
        mapper.setFilterProvider(filters);
        try {
            String s = mapper.writerWithView(JsonViewFilter.OutputA.class).writeValueAsString(user);
            System.out.println(s);
//            InjectableValues inject = new InjectableValues.Std().addValue("rolename", "se");
//            Role role2 = mapper.readerFor(Role.class).with(inject).readValue(roleStr);
//            System.out.println(role2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static User initUser() {
        return new User(1, "java", 8);
    }

    private static Role initRole() {
        return new Role("ee", new Date());
    }
}
