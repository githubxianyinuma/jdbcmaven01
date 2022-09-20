package com.iweb;

import com.iweb.domain.User;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        User user = new User(1, "张三", "123456", new Date(), 20);
        //未重写toString方法，输出的是com.iweb.domain.User@f56a39f1，为内存地址信息
        //重写tostring方法后，输出打印User{id=1, name='张三', passward='123456', birthday=Tue Sep 20 21:07:52 CST 2022, age=20}
        System.out.println(user);
    }
}
