package com.catvgd.springbootdemo.common.util;

import java.io.IOException;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * JSON工具
 * 
 * @author zhuyong
 * @date 2016年12月22日
 */
public class JacksonUtil {

    private static final Logger logger = LoggerFactory.getLogger(JacksonUtil.class);
    private static final ObjectMapper useAnnotationMapper = new ObjectMapper();
    private static final ObjectMapper notUseAnnotationMapper = new ObjectMapper();
    private static final JsonFactory factory = new JsonFactory();
    static {
        // 设置时间
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // 使用注解的mapper
        // 设置日期格式
        useAnnotationMapper.setDateFormat(df);
        // 不把date转换成timestamps
        useAnnotationMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        // 是否使用注解
        useAnnotationMapper.configure(MapperFeature.USE_ANNOTATIONS, true);
        // 忽略不存在的字段
        useAnnotationMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        // 允许没加引号的字段
        useAnnotationMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        // 不使用注解的mapper
        // 设置日期格式
        notUseAnnotationMapper.setDateFormat(df);
        // 不把date转换成timestamps
        notUseAnnotationMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        // 是否使用注解
        notUseAnnotationMapper.configure(MapperFeature.USE_ANNOTATIONS, false);
        // 忽略不存在的字段
        notUseAnnotationMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        // 允许没加引号的字段
        notUseAnnotationMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
    }

    /**
     * 对象转换成字符串(不使用注解)
     * 
     * @param obj
     * @return
     */
    public static String objToJson(Object obj) {
        return objToJson(obj, false);
    }

    /**
     * 对象转换成字符串
     * 
     * @param obj
     * @param useAnnotation
     * @return
     */
    public static String objToJson(Object obj, boolean useAnnotation) {
        String content = "";
        StringWriter writer = new StringWriter();
        JsonGenerator generator = null;
        try {
            generator = factory.createGenerator(writer);
            if (useAnnotation) {
                useAnnotationMapper.writeValue(generator, obj);
            } else {
                notUseAnnotationMapper.writeValue(generator, obj);
            }
        } catch (IOException e) {
            logger.error(e.getMessage());
        } finally {
            try {
                if (generator != null) {
                    generator.close();
                }
                content = writer.toString();
                writer.close();
            } catch (IOException e1) {
                logger.error(e1.getMessage());
            }
        }
        return content;
    }

    /**
     * 字符串转换成指定类型(不使用注解)
     * 
     * @param <T>
     * @param content
     * @param classOfT
     * @return
     */
    public static <T> T jsonToObj(String content, Class<T> classOfT) {
        return jsonToObj(content, classOfT, false);
    }

    /**
     * 字符串转换成指定类型
     * 
     * @param content
     * @param classOfT
     * @param useAnnotation
     * @return
     */
    public static <T> T jsonToObj(String content, Class<T> classOfT, boolean useAnnotation) {
        T obj = null;
        try {
            if (useAnnotation) {
                obj = useAnnotationMapper.readValue(content, classOfT);
            } else {
                obj = notUseAnnotationMapper.readValue(content, classOfT);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return obj;
    }

    /**
     * 字符串转换成指定类型(不使用注解)
     * 
     * @param <T>
     * @param content
     * @param typeOfT
     * @return
     */
    public static <T> T jsonToObj(String content, TypeReference<T> typeOfT) {
        return jsonToObj(content, typeOfT, false);
    }

    /**
     * 字符串转换成指定类型
     * 
     * @param content
     * @param typeOfT
     * @param useAnnotation
     * @return
     */
    public static <T> T jsonToObj(String content, TypeReference<T> typeOfT, boolean useAnnotation) {
        T obj = null;
        try {
            if (useAnnotation) {
                obj = useAnnotationMapper.readValue(content, typeOfT);
            } else {
                obj = notUseAnnotationMapper.readValue(content, typeOfT);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return obj;
    }

    /**
     * 字符串转换为泛型的List&lt;T&gt;对象(不使用注解)
     * 
     * @param content
     * @param classOfT
     * @return
     */
    public static <T> List<T> jsonToListObj(String content, Class<T> classOfT) {
        return jsonToListObj(content, classOfT, false);
    }

    /**
     * 字符串转换为泛型的List&lt;T&gt;对象
     * 
     * @param content
     * @param classOfT
     * @param useAnnotation
     * @return
     */
    public static <T> List<T> jsonToListObj(String content, Class<T> classOfT, boolean useAnnotation) {
        List<T> obj = null;
        try {
            if (useAnnotation) {
                JavaType javaType = useAnnotationMapper.getTypeFactory().constructParametricType(ArrayList.class, classOfT);
                obj = useAnnotationMapper.readValue(content, javaType);
            } else {
                JavaType javaType = notUseAnnotationMapper.getTypeFactory().constructParametricType(ArrayList.class, classOfT);
                obj = notUseAnnotationMapper.readValue(content, javaType);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return obj;
    }

    /**
     * 字符串转换为泛型的Map&lt;K, V&gt;对象(不使用注解)
     * 
     * @param content
     * @param classOfK
     * @param classOfV
     * @return
     */
    public static <K, V> Map<K, V> jsonToMap(String content, Class<K> classOfK, Class<V> classOfV) {
        return jsonToMap(content, classOfK, classOfV, false);
    }

    /**
     * 字符串转换为泛型的Map&lt;K, V&gt;对象
     * 
     * @param content
     * @param classOfK
     * @param classOfV
     * @param useAnnotation
     * @return
     */
    public static <K, V> Map<K, V> jsonToMap(String content, Class<K> classOfK, Class<V> classOfV, boolean useAnnotation) {
        Map<K, V> obj = null;
        try {
            if (useAnnotation) {
                JavaType javaType = useAnnotationMapper.getTypeFactory().constructParametricType(Map.class, classOfK, classOfV);
                obj = useAnnotationMapper.readValue(content, javaType);
            } else {
                JavaType javaType = notUseAnnotationMapper.getTypeFactory().constructParametricType(Map.class, classOfK, classOfV);
                obj = notUseAnnotationMapper.readValue(content, javaType);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return obj;
    }

    /**
     * 字符串转换为泛型的List&lt;Map&lt;K, V&gt;&gt;对象(不使用注解)
     * 
     * @param content
     * @param classOfK
     * @param classOfV
     * @return
     */
    public static <K, V> List<Map<K, V>> jsonToListMap(String content, Class<K> classOfK, Class<V> classOfV) {
        return jsonToListMap(content, classOfK, classOfV, false);
    }

    /**
     * 字符串转换为泛型的List&lt;Map&lt;K, V&gt;&gt;对象
     * 
     * @param content
     * @param classOfK
     * @param classOfV
     * @param useAnnotation
     * @return
     */
    public static <K, V> List<Map<K, V>> jsonToListMap(String content, Class<K> classOfK, Class<V> classOfV, boolean useAnnotation) {
        List<Map<K, V>> obj = null;
        try {
            if (useAnnotation) {
                JavaType subJavaType = useAnnotationMapper.getTypeFactory().constructParametricType(Map.class, classOfK, classOfV);
                JavaType javaType = useAnnotationMapper.getTypeFactory().constructParametricType(List.class, subJavaType);
                obj = useAnnotationMapper.readValue(content, javaType);
            } else {
                JavaType subJavaType = notUseAnnotationMapper.getTypeFactory().constructParametricType(Map.class, classOfK, classOfV);
                JavaType javaType = notUseAnnotationMapper.getTypeFactory().constructParametricType(List.class, subJavaType);
                obj = notUseAnnotationMapper.readValue(content, javaType);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return obj;
    }

}
