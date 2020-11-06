package com.ssycoding.iwebservice.cxfservice;

import com.ssycoding.iwebservice.cxfservice.model.User;
import com.ssycoding.iwebservice.cxfservice.model.Users;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

import java.util.ArrayList;

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
        Client client = jaxWsDynamicClientFactory.createClient("http://localhost:8080/demo/api?wsdl");

        Users users = new Users();
        ArrayList<User> userArrayList = new ArrayList<>();
        userArrayList.add(new User("joker", 26, "abc"));
        userArrayList.add(new User("sean", 25, "def"));
        users.setUsers(userArrayList);

        try {
            Object[] result = client.invoke("getUserInfo", users);
            String s = result[0].toString();
            System.out.println(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
