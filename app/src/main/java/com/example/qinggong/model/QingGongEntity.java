package com.example.qinggong.model;

/**
 * Created by ZJGJK03 on 2014/11/25.
 */
public class QingGongEntity {
    public QingGongEntity() {
    }

    public QingGongEntity(String age, String month, String sex) {
        this.age = age;
        this.month = month;
        this.sex = sex;
    }

    public String age,month,sex;

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
