package com.ssycoding.iannotation.demo.annotaion;

import com.ssycoding.iannotation.demo.aspect.EncryCertNoContraintValidated;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @ClassName: EncryCertNo
 * @Description: 自定义：身份证格式校验
 * @Author Sean
 * @Date 2020/6/19 16:11
 * @Version 1.0
 */
@Documented // 表明该注解标记的元素可以被JavaDoc或类似的工具文档化
@Target(ElementType.FIELD) // 表明该注解可以应用的Java元素类型
@Retention(RetentionPolicy.RUNTIME) // 表明该注解的生命周期
@Repeatable(EncryCertNo.List.class)
@Constraint(validatedBy = EncryCertNoContraintValidated.class)
public @interface EncryCertNo {

    String message() default "{com.iutils.demo.annotation.entry.EncryCertNo.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    @Target(ElementType.FIELD)
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    public @interface List {
        EncryCertNo[] value();
    }

}