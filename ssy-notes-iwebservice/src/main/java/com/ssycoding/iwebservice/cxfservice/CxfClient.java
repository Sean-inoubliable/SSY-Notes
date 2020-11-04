package com.ssycoding.iwebservice.cxfservice;

import com.ssycoding.iwebservice.cxfservice.model.User;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

/**
 * @Description: 测试客户端
 * @Author: Sean
 * @Date: 2020/10/28 15:24
 */
public class CxfClient {

    public static void main(String[] args) {
        CxfClient cxfClient = new CxfClient();
        cxfClient.testClient();
    }

    public void testClient() {
        JaxWsDynamicClientFactory jaxWsDynamicClientFactory = JaxWsDynamicClientFactory.newInstance();
        Client client = jaxWsDynamicClientFactory.createClient("http://localhost:8081/demo/api?wsdl");

        User user = new User();

        try {
            Object[] sayHellos = client.invoke("getUserInfo", user);
            for (Object o : sayHellos) {
                System.out.println("返回数据：" + o);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
