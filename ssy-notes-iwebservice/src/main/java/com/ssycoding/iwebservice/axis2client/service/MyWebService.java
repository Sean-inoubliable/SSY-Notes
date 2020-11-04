package com.ssycoding.iwebservice.axis2client.service;


import com.ssycoding.iwebservice.axis2client.model.User;

import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * @Comments: 对外提供的WebService方法
 * @Author: Sean
 * @Date: 2020-03-18 20:10
 */
@WebService
public class MyWebService {

    /**
     * @Author: Sean
     * @Date: 2020/3/25 15:57
     * @Description: 入参参数为基本参数，出参参数为对象
     * @Param:
     * @Return:
     */
    public User userMethod(@WebParam(name = "name") String name,
                           @WebParam(name = "age") Integer age,
                           @WebParam(name = "motto") String motto) {

        System.out.println("-----> 进入 userMethod 方法（）");

        User user = new User();
        user.setMyName(name+"Joker");
        user.setMyAge(age);
        user.setMyMotto(motto);

        return user;
    }
}
