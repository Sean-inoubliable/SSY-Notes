package com.ssycoding.iwebservice.axis2client.service;

import javax.xml.ws.Endpoint;

/**
 * @Comments: 发布 WebService 服务
 * @Author: Sean
 * @Date: 2020-03-18 20:14
 */
public class MyService {

    public static void main(String[] args) {
        // 自己设置一个地址
        String address = "http://127.0.0.1:9527/webservice/test";
        Endpoint.publish(address, new MyWebService());
    }
}
