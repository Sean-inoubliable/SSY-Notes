package com.ssycoding.iwebservice.cxfservice.model;

/**
 * @Comments: 用来 辅助演示
 * @Author: Sean
 * @Date: 2020-03-18 20:08
 */
public class User {
    /* 姓名 */
    private String myName;
    /* 年龄 */
    private Integer myAge;
    /* 座右铭 */
    private String myMotto;

    public User(String joker, int i, String abc) {
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("User{");
        sb.append("myName='").append(myName).append('\'');
        sb.append(", myAge=").append(myAge);
        sb.append(", myMotto='").append(myMotto).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public String getMyName() {
        return myName;
    }

    public void setMyName(String myName) {
        this.myName = myName;
    }

    public Integer getMyAge() {
        return myAge;
    }

    public void setMyAge(Integer myAge) {
        this.myAge = myAge;
    }

    public String getMyMotto() {
        return myMotto;
    }

    public void setMyMotto(String myMotto) {
        this.myMotto = myMotto;
    }
}
