package com.ssycoding.filter.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description: 一个简单的过滤器
 * @Author: Sean
 * @Date: 2020/11/5 11:29
 */
@Slf4j
@Service
@WebFilter(filterName = "SeanFilter", urlPatterns = "/*")
public class SeanFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        /* 可以加载 Filter启动之前需要的资源 */
        log.info(" ------- seanFilter init ------- ");
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info(" ------- seanFilter doFilter executed ------- ");

        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;

        log.info("URI: " + request.getRequestURI());
        log.info("URL: " + request.getRequestURL());
        log.info("Method: " + request.getMethod());
        log.info("CharacterEncoding: " + request.getCharacterEncoding());

        // -----------------------------------------------------------------------

//        Map<String, String[]> parameterMap = request.getParameterMap();
//        for (String key : parameterMap.keySet()) {
//            System.out.println(key + " = " + parameterMap.get(key)[0]);
//        }

        // -----------------------------------------------------------------------

        filterChain.doFilter(servletRequest, servletResponse);

        log.info("servletResponse: " + response);
        log.info(" ------- seanFilter doFilter after ------- ");
    }

    @Override
    public void destroy() {
        log.info(" ------- seanFilter Destroy ------- ");
    }
}
