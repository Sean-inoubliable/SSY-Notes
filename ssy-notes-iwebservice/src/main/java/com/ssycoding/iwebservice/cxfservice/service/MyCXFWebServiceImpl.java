package com.ssycoding.iwebservice.cxfservice.service;

import com.ssycoding.iwebservice.cxfservice.model.User;
import com.ssycoding.iwebservice.cxfservice.model.Users;

import javax.jws.WebService;

/**
 * 服务端实现
 */
@WebService(serviceName = "MyCXFWebService", // 与接口中指定的name一致
        targetNamespace = "http://model.cxfservice.iwebservice.ssycoding.com", // 与接口中的命名空间一致,一般是接口的包名倒
        endpointInterface = "com.ssycoding.iwebservice.cxfservice.service.MyCXFWebService"// 接口地址
)
public class MyCXFWebServiceImpl implements MyCXFWebService {

    @Override
    public Users getUserInfo(Users users) {

        System.out.println("服务端接收数据: "  +  users);

        return users;
    }

}