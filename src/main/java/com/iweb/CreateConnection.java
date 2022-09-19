package com.iweb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 建立数据库连接
 */
public class CreateConnection {

    public static void main(String[] args) {
        /**
         * jdbc:mysql://协议
         * IP：127.0.0.1
         * prot：端口3306
         * study：数据库的名字
         * useUnicode=true使用Unicode编码
         * characterEncoding=UTF-8
         * userSSL=false 未使用SSL
         * serverTimezone=GMT%2B8 服务器全球时区 GMT%2B8代表东八区，%2B实际为+，因为是一个字符
         */
        String url = "jdbc:mysql://127.0.0.1:3306/study?useUnicode=true&characterEncoding=UTF-8&userSSL=false&serverTimezone=GMT%2B8";
        String username = "root";
        String pw = "123456";
        //Java连接mysql,mysql提供的驱动程序存放于JAR包里面，这里调用驱动程序
        String driverClassName = "com.mysql.cj.jdbc.Driver";

        //1、加载驱动程序,Class.forName固定写法
        try {
            Class.forName(driverClassName);//Java反射技术 侯杰谈Java，Java编程思想侯杰PDF
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        //2、连接数据库，DriverManager.getConnection往数据库写入用户信息，println输出代表已经连接
        try {
            Connection conn = DriverManager.getConnection(url, username, pw);
            System.out.println(conn);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
