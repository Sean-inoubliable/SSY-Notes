package com.ssycoding.ilog.weblog.pojo;

/**
 * @Comments:
 * @Author: Sean
 * @Date: 2020/5/16 19:59
 */
public class Person {

    private String name;

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
