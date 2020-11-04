package com.ssycoding.ilog.weblog.entry;

import java.lang.annotation.*;

/**
 * @Comments: 自定义：日志注解
 * @Author: Sean
 * @Date: 2020/5/16 19:56
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface WebLog {

    /**
     * 日志描述信息
     * @return
     */
    String description() default "";
}
