package io.damo.common.json;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.google.common.collect.Lists;
import io.damo.common.utils.A;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * Created by pinshiern on 2017/7/31.
 */
public class JSONUtil {
    private static ObjectMapper om = new ObjectMapper();

    public JSONUtil() {
    }

    public static String getJSON(Object source) {
        try {
            return om.writeValueAsString(source);
        } catch (JsonProcessingException var2) {
            return null;
        }
    }

    /** 只有一些基础规则的 json 映射器. 需要序列化和反序列化时, 就使用这个映射器 */
    private static final ObjectMapper BASIC = new BasicObjectMapper();

    /**
     * 用来渲染给前台的 json 映射器, 定义了一些自定义类的序列化规则, 然而并没有反序列化规则<br>
     * 所以使用此映射器序列化的 json, 想要反序列化回来调用(toObject toList)时将会不成功
     */
    public static final ObjectMapper RENDER = new RenderObjectMapper();

    private static class BasicObjectMapper extends ObjectMapper {
        private BasicObjectMapper() {
            super();
            // 日期不用 utc 方式显示(utc 是一个整数值)
            // configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
            // 时间格式
            // setDateFormat(new SimpleDateFormat(DateFormatType.YYYY_MM_DD_HH_MM_SS.getValue()));
            // 不确定值的枚举返回 null
            configure(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL, true);
            // 不确定的属性项上不要失败
            configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        }
    }

    private static class RenderObjectMapper extends BasicObjectMapper {
        private RenderObjectMapper() {
            super();
            // 序列化 PageList 时只将总条数和当前页的数据返回. 否则可以使用 PageListJsonSerializer 这个现成的实现
            // registerModule(new SimpleModule().addSerializer(new PageListJsonSerializer(this)));
            registerModule(new SimpleModule().addSerializer(PageList.class, new JsonSerializer<PageList>() {
                @Override
                public void serialize(PageList value, JsonGenerator gen, SerializerProvider sp) throws IOException {
                    gen.writeObject(A.maps(
                            "items", Lists.newArrayList(value),
                            "total", value.getPaginator().getTotalCount()
                    ));
                }
            }));
        }
    }

    /** 对象转换成 json 字符串 */
    public static String toJson(Object obj) {
        return toJson(BASIC, obj);
    }

    public static Object fromString(String jsonString, Class classZ) {
        //如果json文本里有的字段，但是Java类中没有定义，那就忽略改字段
        BASIC.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        try {
            return BASIC.readValue(jsonString, classZ);
        } catch (Exception e) {
            throw new RuntimeException("json to object exception.", e);
        }
    }


    private static String toJson(ObjectMapper om, Object obj) {
        try {
            return om.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException("object(" + obj + ") to json exception.", e);
        }
    }

    /** 对象转换成 json 字符串, 主要用来渲染到前台 */
    public static String toRender(Object obj) {
        return toJson(RENDER, obj);
    }

    /** 将 json 字符串转换为对象 */
    public static <T> T toObject(String json, Class<T> clazz) {
        try {
            return BASIC.readValue(json, clazz);
        } catch (Exception e) {
            throw new RuntimeException("json (" + json + ") to object(" + clazz.getName() + ") exception", e);
        }
    }

    /** 将 json 字符串转换为对象, 当转换异常时, 返回 null */
    public static <T> T toObjectNil(String json, Class<T> clazz) {
        try {
            return BASIC.readValue(json, clazz);
        } catch (Exception e) {
            return null;
        }
    }

    /** 将 json 字符串转换为指定的数组列表 */
    public static <T> List<T> toList(String json, Class<T> clazz) {
        try {
            return BASIC.readValue(json, BASIC.getTypeFactory().constructCollectionType(List.class, clazz));
        } catch (Exception e) {
            throw new RuntimeException("json(" + json + ") to list(" + clazz.getName() + ") exception.", e);
        }
    }
    /** 将 json 字符串转换为指定的数组列表 */
    public static <T> List<T> toListNil(String json, Class<T> clazz) {
        try {
            return BASIC.readValue(json, BASIC.getTypeFactory().constructCollectionType(List.class, clazz));
        } catch (Exception e) {
            return null;
        }
    }


    // ========== 返回自定义属性 ==========

    private static final String CUSTOM_FILTER = "customFilter";

    @JsonFilter(CUSTOM_FILTER)
    private static class CustomFilterMixin {
    }

    /**
     * <pre>
     * 只输出传入的属性, 支持级联! 参考:
     * https://github.com/Antibrumm/jackson-antpathfilter<br>
     * 此方法是为了输出 json 字符串, 在 controller 中应该调用 {@link #toObjectWithField}
     *
     * public class User {
     * 	Long id;
     * 	String name;
     * 	String password;
     * 	Msg info;
     * }
     * public class Msg {
     * 	Long id;
     * 	String name;
     * }
     *
     * User user = new User(123l, "ruby", "encrypt-code", new Msg(890l, "abc123"));
     *
     * // 输出 {"id":123,"name":"ruby","password":"encrypt-code","info":{"id":890,"name":"abc123"}}
     * toJsonWithField(user);
     *
     * // 输出 {"name":"ruby","info":{}}
     * toJsonWithField(user, "name", "info");
     *
     * // 输出 {"name":"ruby"}
     * toJsonWithField(user, "name", "info.name");
     *
     * // 输出 {"name":"ruby","info":{"name":"abc123"}}
     * toJsonWithField(user, "name", "phone", "info", "info.name");
     *
     * // 输出 {"id":123,"name":"ruby","password":"encrypt-code","info":{}}
     * toJsonWithField(user, "*");
     *
     * // 输出 {"id":123,"name":"ruby","info":{}} <span style="color:red;">星号代表全部, 感叹(!) 和 减号(-) 都能排除属性</span>
     * toJsonWithField(user, "*", "!password");
     *
     * // 输出 {"id":123,"name":"ruby","info":{"id":890,"name":"abc123"}}
     * toJsonWithField(user, "**", "!password");
     *
     * // 输出 {"id":123,"name":"ruby","info":{"name":"abc123"}}
     * toJsonWithField(user, "**", "!password", "-info.id");
     * </pre>
     *
     * @see #toJson(Object)
     */
    public static String toJsonWithField(Object obj, String... fields) {
        if (obj == null) return null;
        return toRender(toObjectWithField(obj, fields));
    }

    /** 基于 spring mvc 的设置, 当前方法返回的对象会被 {@link #toRender} 渲染后才会返回到前台 */
    public static Object toObjectWithField(Object obj, String... fields) {
        if (obj == null) return null;

        if (obj instanceof PageList) {
            // pageList 只操作里面的 List
            return customField((PageList) obj, fields);
        } else {
            String json = customField(obj, fields);
            // 返回使用 Object 将会是一个 LinkedHashMap 与原对象无关, 如果返回原对象, 对象上有默认值也将会被序列化
            // return (obj instanceof List) ? toList(json, Object.class) : toObject(json, Object.class);
            if (obj instanceof List) {
                // 将过滤好的字符串「反序列化」成一个 List 并返回
                if (A.isEmpty((List) obj)) {
                    return toList(json, Object.class);
                } else {
                    return toList(json, ((List) obj).iterator().next().getClass());
                }
            } else {
                // 将过滤好的字符串「反序列化」成一个 Object 并返回
                return toObject(json, obj.getClass());
            }
        }
    }

    /** 如果传入的是 List, 按照传入的参数进行过滤后再次返回 List */
    public static <T> List<T> toListWithField(List<T> list, String... fields) {
        if (A.isEmpty(list)) return Collections.emptyList();

        if (list instanceof PageList) {
            // pageList 只操作里面的 List
            return customField((PageList) list, fields);
        } else {
            // 将过滤好的字符串「反序列化」成 List 并返回
            return toList(customField(list, fields), (Class<T>) list.iterator().next().getClass());
        }
    }

    private static PageList customField(PageList pageList, String... fields) {
        if (A.isEmpty(pageList)) return pageList;

        String json = customField(A.lists(pageList.toArray()), fields);
        // 将上面的字符串反序列化成一个 list
        List list = toList(json, pageList.iterator().next().getClass());

        // 将 pageList 清空并将上面的 list 重新添加进来
        pageList.clear();
        pageList.addAll(list);
        return pageList;
    }

    /** 将对象过滤掉相关属性并序列化成一个字符串返回 */
    private static String customField(Object obj, String... fields) {
        // 构建一个专门用来过滤字段的映射器
        ObjectMapper om = new BasicObjectMapper();
        // 过滤属性时会改变映射器的一些内部信息, 因此要每次都实例化一个映射器. 随之而来的代价就是性能会稍差一点
        om.addMixIn(Object.class, CustomFilterMixin.class);
        om.setFilterProvider(new SimpleFilterProvider().addFilter(CUSTOM_FILTER, new AntPathPropertyFilter(fields)));

        // 使用此映射器序列化对象成一个字符串
        return toJson(om, obj);
    }
}
