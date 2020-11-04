package com.ssycoding.iannotation.demo.pojo;

import com.ssycoding.iannotation.demo.annotaion.EncryCertNo;

/**
 * @Comments:
 * @Author: Sean
 * @Date: 2020/4/16 15:03
 */
public class Person {

    private String name;

    @EncryCertNo(message = "$certNo 不能为空")
    private String certNo;

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", certNo='" + certNo + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCertNo() {
        return certNo;
    }

    public void setCertNo(String phone) {
        this.certNo = phone;
    }
}
