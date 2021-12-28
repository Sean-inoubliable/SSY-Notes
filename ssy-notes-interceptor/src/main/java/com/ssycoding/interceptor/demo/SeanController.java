package com.ssycoding.interceptor.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: Sean
 * @Date: 2021/5/8 11:12
 */
@Slf4j
@RestController
public class SeanController {

    @RequestMapping("/test")
    public String test(String name) {
        log.info("正在 控制层 进行处理 ... ... ");
        return name;
    }
}
