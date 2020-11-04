package com.ssycoding.iwebservice.axis2client.webservice;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @Comments: HTTP工具类
 * @Author: Sean
 * @Date: 2020-03-18 23:12
 */
public class HttpSendUtil {
    /**
     * 使用apache的HttpClient发送http
     *
     * @param wsdlURL
     *            请求URL
     * @param contentType
     *            如:application/json;charset=utf8
     * @param content
     *            数据内容
     * @DATE 2018年9月22日 下午10:29:17
     */
    static String doHttpPostByHttpClient(final String wsdlURL, final String contentType, final String content)
            throws ClientProtocolException, IOException {
        // 获得Http客户端(可以理解为:你得先有一个浏览器;注意:实际上HttpClient与浏览器是不一样的)
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        // 创建Post请求
        HttpPost httpPost = new HttpPost(wsdlURL);
        StringEntity entity = new StringEntity(content.toString(), "UTF-8");
        // 将数据放入entity中
        httpPost.setEntity(entity);
        httpPost.setHeader("Content-Type", contentType);
        // 响应模型
        CloseableHttpResponse response = null;
        String result = null;
        try {
            // 由客户端执行(发送)Post请求
            response = httpClient.execute(httpPost);
            // 从响应模型中获取响应实体
            // 注意:和doHttpPostByRestTemplate方法用的不是同一个HttpEntity
            org.apache.http.HttpEntity responseEntity = response.getEntity();
            System.out.println("响应ContentType为:" + responseEntity.getContentType());
            System.out.println("响应状态为:" + response.getStatusLine());
            if (responseEntity != null) {
                result = EntityUtils.toString(responseEntity);
                System.out.println("响应内容为:" + result);
            }
        } finally {
            // 释放资源
            if (httpClient != null) {
                httpClient.close();
            }
            if (response != null) {
                response.close();
            }
        }
        return result;
    }

    /**
     * 使用springframework的RestTemplate发送http
     *
     * @param wsdlURL
     *            请求URL
     * @param contentType
     *            如:application/json;charset=utf8
     * @param content
     *            数据内容
     * @DATE 2018年9月22日 下午10:30:48
     */
    static String doHttpPostByRestTemplate(final String wsdlURL, final String contentType, final String content) {
        // http使用无参构造;https需要使用有参构造
        RestTemplate restTemplate = new RestTemplate();
        // 解决中文乱码
        List<HttpMessageConverter<?>> converterList = restTemplate.getMessageConverters();
        converterList.remove(1);
        HttpMessageConverter<?> converter = new StringHttpMessageConverter(StandardCharsets.UTF_8);
        converterList.add(1, converter);
        restTemplate.setMessageConverters(converterList);
        // 设置Content-Type
        HttpHeaders headers = new HttpHeaders();
        headers.remove("Content-Type");
        headers.add("Content-Type", contentType);
        // 数据信息封装
        // 注意:和doHttpPostByHttpClient方法用的不是同一个HttpEntity
        org.springframework.http.HttpEntity<String> formEntity = new org.springframework.http.HttpEntity<String>(
                content, headers);
        String result = restTemplate.postForObject(wsdlURL, formEntity, String.class);
        return result;
    }

}
