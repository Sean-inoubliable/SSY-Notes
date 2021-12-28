package com.ssycoding.interceptor.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description:
 * @Author: Sean
 * @Date: 2021/5/8 11:12
 */
@Slf4j
@Service
public class SeanInterceptor implements HandlerInterceptor {

    /**
     * 在DispatcherServlet之前执行
     * 进入controller层之前拦截请求
     * */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        log.info(" ------- seanInterceptor preHandler executed ... ... ");
        return true;
    }

    /**
     * 在controller执行之后的DispatcherServlet之后执行
     * 处理请求完成后视图渲染之前的处理操作
     * */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        log.info(" ------- seanInterceptor postHandle executed ... ... ");
    }

    /**
     * 在页面渲染完成返回给客户端之前执行
     * 视图渲染之后的操作
     * */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        log.info(" ------- seanInterceptor afterCompletion executed ... ... ");
    }
}
