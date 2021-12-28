package com.ssycoding.iannotation.demo.action;

import com.ssycoding.iannotation.demo.pojo.Person;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 注解测试 Action
 * @Author: Sean
 * @Date: 2020/5/20 10:34
 */
@Slf4j
@RestController
public class AnnotationAction {

    @RequestMapping(value = "/annotationtest")
    public Person AnnotationTest(@Validated Person person, BindingResult bindingResult) {

        if (ObjectUtils.isNotEmpty(bindingResult.getFieldError())) {
            log.error(bindingResult.getFieldError().getDefaultMessage());
        }
        log.info(person.toString());
        return person;
    }
}
