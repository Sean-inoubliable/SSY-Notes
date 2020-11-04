package com.ssycoding.iwebservice.cxfservice.service;

import com.ssycoding.iwebservice.cxfservice.model.User;

import javax.jws.WebService;

/**
 * 服务端接口
 */
@WebService(name = "MyCXFWebService", // 暴露服务名称
        targetNamespace = "http://service.cxfservice.iwebservice.ssycoding.com"// 命名空间,一般是接口的包名倒序
)
public interface MyCXFWebService {

    public User getUserInfo(User user);

}