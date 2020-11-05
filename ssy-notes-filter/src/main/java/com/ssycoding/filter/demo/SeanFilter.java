package com.ssycoding.filter.demo;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@Service
@WebFilter(filterName = "SeanFilter", urlPatterns = "/*")
public class SeanFilter implements Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger(SeanFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        /* 可以加载 Filter启动之前需要的资源 */
        LOGGER.info(" ------- seanFilter init ------- ");
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        LOGGER.info(" ------- seanFilter doFilter executed ------- ");

        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;

        LOGGER.info("URI: " + request.getRequestURI());
        LOGGER.info("URL: " + request.getRequestURL());
        LOGGER.info("Method: " + request.getMethod());
        LOGGER.info("CharacterEncoding: " + request.getCharacterEncoding());

        // -----------------------------------------------------------------------

//        Map<String, String[]> parameterMap = request.getParameterMap();
//        for (String key : parameterMap.keySet()) {
//            System.out.println(key + " = " + parameterMap.get(key)[0]);
//        }

        // -----------------------------------------------------------------------

        filterChain.doFilter(servletRequest, servletResponse);

        LOGGER.info("servletResponse: " + response);
        LOGGER.info(" ------- seanFilter doFilter after ------- ");
    }

    @Override
    public void destroy() {
        LOGGER.info(" ------- seanFilter Destroy ------- ");
    }
}
