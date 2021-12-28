package com.ssycoding.ilog.weblog.action;

import com.ssycoding.ilog.weblog.entry.WebLog;
import com.ssycoding.ilog.weblog.pojo.Person;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Comments:
 * @Author: Sean
 * @Date: 2020/5/16 19:58
 */
@Slf4j
@RestController
public class LogController {

    @WebLog(description = "测试自定义日志 - Controller")
    @RequestMapping(value = "/log")
    public Person AnnotationTest(@Validated Person person, BindingResult bindingResult) {

        if (!ObjectUtils.isEmpty(bindingResult.getFieldError())) {
            log.error(bindingResult.getFieldError().getDefaultMessage());
        }
        log.info(person.toString());
        return person;
    }
}
