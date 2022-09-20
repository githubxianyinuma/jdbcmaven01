package com.iweb.domain;

import java.util.Date;

/**
 * 1、所有的成员变量都必须是私有的（外部访问不到）
 * 2、所有的成员变量通过set方法赋值，通过get方法获取值，ALT+Insert getter and setter
 * 3、必须要有无参的构造方法，也可以有有参数的构造方法ALT+Insert constructor
 * 4、必须重写hashcode和equals方法
 * 5、一般情况下，建议重写toString方法
 * 6、要实现序列化
 */

public class User {
    //1、所有的成员变量都必须是私有的,人这个类有5个成员变量
    private Integer id;//未来成员变量只允许使用包装类integeger
    private String name;
    private String passward;
    private Date birthday;
    private Integer age;
    //5、一般情况下，建议重写toString方法
    /**
     * 方便我们看一个新对象有什么东西
     */
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", passward='" + passward + '\'' +
                ", birthday=" + birthday +
                ", age=" + age +
                '}';
    }
    //4、必须重写hashcode和equals方法
    /**
     *equals判断两个对象是否相等
     * hashcode表示任何对象都有哈希值
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != null ? !id.equals(user.id) : user.id != null) return false;
        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        if (passward != null ? !passward.equals(user.passward) : user.passward != null) return false;
        if (birthday != null ? !birthday.equals(user.birthday) : user.birthday != null) return false;
        return age != null ? age.equals(user.age) : user.age == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (passward != null ? passward.hashCode() : 0);
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        result = 31 * result + (age != null ? age.hashCode() : 0);
        return result;
    }

    //3、必须要有无参的构造方法
    public User() {
    }
    //3、也可以有有参数的构造方法
    public User(Integer id, String name, String passward, Date birthday, Integer age) {
        this.id = id;
        this.name = name;
        this.passward = passward;
        this.birthday = birthday;
        this.age = age;
    }
    //2、所有的成员变量通过set方法赋值，通过get方法获取值
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassward() {
        return passward;
    }

    public void setPassward(String passward) {
        this.passward = passward;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
