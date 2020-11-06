package com.ssycoding.iwebservice.cxfservice.model;

import java.util.List;

/**
 * @Description: 包装对象
 * @Author: Sean
 * @Date: 2020/11/6 13:39
 */
public class Users {

    private List<User> users;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Users{" +
                "users=" + users +
                '}';
    }
}
