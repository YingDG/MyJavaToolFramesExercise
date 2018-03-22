package yingdg.exercise.jackson;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.introspect.AnnotatedField;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Created by yingdg on 2017/6/8.
 * <p>
 * Jackson提供了三种可选的Json处理方法：
 * 数据绑定(Data Binding)，流式API(Streaming API)，树模型(Tree Model)
 */
public final class JacksonUtil {
    // jackson核心类
    private static final ObjectMapper MAPPER = new ObjectMapper();

    static {
        initConfig(MAPPER);
    }

    /*
    数据绑定(Data Binding)方式
    */
    public static String toJson(Object obj) {
        String json = null;
        try {
            json = MAPPER.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return json;
    }

    public static <T> T toObject(String json, Class<T> clz) {
        Object obj = null;
        try {
            obj = MAPPER.readValue(json, clz);

             /*
            范型解析
             */
            // mapper.readValue("", new TypeReference<Map<String, Object>>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }

        return (T) obj;
    }

    private static void initConfig(ObjectMapper mapper) {
        /*
        当反序列化json时，未知属性会引起的反序列化被打断，这里禁用未知属性打断反序列化功能，
        例如json里有10个属性，而bean中只定义了2个属性，其它8个属性将被忽略
         */
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        /*
        通用日期格式处理
         */
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()));
        /*
        让Json可以缩进，可读性更好（json格式化）
         */
        mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        /*
         默认情况下java类属性名就是输出的json字段名，但是可以采用注解的方式修改。
         如果想java类和json绑定或者无法修改java类，可以采用如下另外一种方式：
         重写如下两个方法，这个取决属性是public或者getter是public
         */
        mapper.setPropertyNamingStrategy(new PropertyNamingStrategy() {
            @Override
            public String nameForField(MapperConfig<?> config, AnnotatedField field, String defaultName) {
                return super.nameForField(config, field, defaultName);
            }

            @Override
            public String nameForGetterMethod(MapperConfig<?> config, AnnotatedMethod method, String defaultName) {
                return super.nameForGetterMethod(config, method, defaultName);
            }
        });
        /*
        如果属性没有值，那么Json是会处理的，int类型为0，String类型为null，数组为[]，
        设置这个特性可以忽略空值属性
         */
        mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        /*
        字母顺序排序属性
         */
        mapper.configure(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, true);
        /*
        是否环绕根元素，默认false，如果为true，则默认以类名作为根元素
        配合@JsonRootName注解使用
         */
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, true);
        /*
        序列化日期时以timestamps输出，默认true
         */
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, true);
        /*
        序列化枚举是以toString()来输出，默认false，即默认以name()来输出
         */
        mapper.configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true);
        /*
        序列化枚举是以ordinal()来输出，默认false
         */
        mapper.configure(SerializationFeature.WRITE_ENUMS_USING_INDEX, false);
        /*
        序列化单元素数组时不以数组来输出，默认false
         */
        mapper.configure(SerializationFeature.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED, false);
        /*
        序列化Map时对key进行排序操作，默认false
         */
        mapper.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, false);
        /*
        序列化char[]时以json数组输出，默认false
         */
        mapper.configure(SerializationFeature.WRITE_CHAR_ARRAYS_AS_JSON_ARRAYS, false);
        /*
        可以配置一个全局的可见级别，通过mapper.setVisibilityChecker()来实现，
        默认的VisibilityChecker实现类为VisibilityChecker.Std，这样可以满足实现复杂场景下的基础配置
         */
        mapper
                .setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY) // auto-detect all member fields
                .setVisibility(PropertyAccessor.GETTER, JsonAutoDetect.Visibility.NONE) // but only public getters
                .setVisibility(PropertyAccessor.IS_GETTER, JsonAutoDetect.Visibility.NONE); // and none of "is-setters"
        /*
        禁止所有的自动检测功能，
        None表示只有注解的属性才会被解析
         */
        // mapper.setVisibility(mapper.getVisibilityChecker().with(JsonAutoDetect.Visibility.NONE));
    }

}
