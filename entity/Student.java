package cn.edu.hit.entity;

import java.sql.Date;

public class Student {
    //定义类，首先需要定义所有的变量，并把可见性定为私有
    private String sid;
    private String name;
    private int age;
    private String birthday;
    private String gender;
    //关于以下的一长串方法，都可以通过右键-生成-构造器/setter和getter完成
    //经典构造器
    public Student(String sid, String name, int age, String birthday,String gender) {
        this.sid = sid;
        this.name = name;
        this.age = age;
        this.birthday = birthday;
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
}
