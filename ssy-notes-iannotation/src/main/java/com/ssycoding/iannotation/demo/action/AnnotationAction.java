package com.ssycoding.iannotation.demo.action;

import com.ssycoding.iannotation.demo.pojo.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 注解测试 Action
 * @Author: Sean
 * @Date: 2020/5/20 10:34
 */
@RestController
public class AnnotationAction {
    private static final Logger logger = LoggerFactory.getLogger(AnnotationAction.class);

    @RequestMapping(value = "/annotationtest")
    public Person AnnotationTest(@Validated Person person, BindingResult bindingResult) {

        if (!ObjectUtils.isEmpty(bindingResult.getFieldError())) {
            logger.error(bindingResult.getFieldError().getDefaultMessage());
        }
        logger.info(person.toString());
        return person;
    }
}
