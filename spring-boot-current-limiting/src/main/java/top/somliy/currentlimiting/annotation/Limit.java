package top.somliy.currentlimiting.annotation;

import top.somliy.currentlimiting.enums.LimitType;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 类名： @ClassName Limit 限流注解
 * 创建人：@author zhao dong
 * 类描述：@Description: 限流注解
 * 创建时间: 2023/7/25 17:37
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Limit {
    /**
     * 名字
     */
    String name() default "";

    /**
     * key
     */
    String key() default "";

    /**
     * Key的前缀
     */
    String prefix() default "";

    /**
     * 给定的时间范围 单位(秒)
     */
    int period();

    /**
     * 一定时间内最多访问次数
     */
    int count();

    /**
     * 限流的类型(用户自定义key 或者 请求ip) 默认方法
     */
    LimitType limitType() default LimitType.METHOD;
}
