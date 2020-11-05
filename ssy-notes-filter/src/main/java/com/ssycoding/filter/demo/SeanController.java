package com.ssycoding.filter.demo;

import com.sun.org.glassfish.gmbal.ParameterNames;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: Sean
 * @Date: 2020/11/5 11:33
 */
@RestController
public class SeanController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SeanController.class);

    @RequestMapping("/test")
    public String test(String name) {
        LOGGER.info("正在 控制层 进行处理 ... ... ");
        return name;
    }
}
