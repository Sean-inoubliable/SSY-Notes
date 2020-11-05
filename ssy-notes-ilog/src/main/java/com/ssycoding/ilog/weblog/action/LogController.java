package com.ssycoding.ilog.weblog.action;

import com.ssycoding.ilog.weblog.entry.WebLog;
import com.ssycoding.ilog.weblog.pojo.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Comments:
 * @Author: Sean
 * @Date: 2020/5/16 19:58
 */
@RestController
public class LogController {

    private static final Logger logger = LoggerFactory.getLogger(LogController.class);

//    @WebLog(description = "测试自定义日志 - Controller")
    @RequestMapping(value = "/log")
    public Person AnnotationTest(@Validated Person person) {

//        if (!ObjectUtils.isEmpty(bindingResult.getFieldError())) {
//            logger.error(bindingResult.getFieldError().getDefaultMessage());
//        }
        logger.info(person.toString());
        return person;
    }
}
