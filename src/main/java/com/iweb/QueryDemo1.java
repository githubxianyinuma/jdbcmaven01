package com.iweb;

import com.sun.corba.se.impl.orbutil.concurrent.Sync;

import java.sql.*;
import java.util.Scanner;

public class QueryDemo1 {
    public static void main(String[] args) {
        String url = "jdbc:mysql://127.0.0.1:3306/study?useUnicode=true&characterEncoding=UTF-8&userSSL=false&serverTimezone=GMT%2B8";
        String username = "root";
        String pw = "123456";
        //Java连接mysql,mysql提供的驱动程序存放于JAR包里面，这里调用驱动程序
        String driverClassName = "com.mysql.cj.jdbc.Driver";
        //1、加载驱动
        try {
            Class.forName(driverClassName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //2、建立连接
        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            conn = DriverManager.getConnection(url, username, pw);
            //scanner能获取键盘上输入的字符串
            Scanner scanner = new Scanner(System.in);
            System.out.println("请输入用户名：");
            String name = scanner.nextLine();//获取键盘输入初值，放到name变量
            System.out.println("请输入密码：");
            String passward = scanner.nextLine();
            //输出输入的结果
            System.out.println(name+" "+passward);
            //3、预编译SQl
            statement = conn.createStatement();
            //4、执行SQL
            String sql = "select COUNT(*) SUM from t_user where name = '"+name+"' and passward = '"+passward+"'";
            //这里这个查询存在SQL注入漏洞，我们使用SQL注入万能密码'or'1'='1，可以成功登录
            //解释如下：https://ymjin.blog.csdn.net/article/details/109263343?spm=1001.2101.3001.6650.1&depth_1-utm_relevant_index=2
            //主要是没有对登录密码的字符串进行参数化和过滤，所以导致网站可以直接用“万能密码”进行突破登录，or是或者的意思，也就是Password=xxx的时候可以登录，也可以是1=1的时候可以登录，要知道，1永远等于1，所以登录条件永远成立，所以永远可以登录。
            resultSet = statement.executeQuery(sql);
            System.out.println(sql);
            resultSet.next();//让游标指向下一行，目的是查看对比sum的值
            int sum = resultSet.getInt("sum");
            //这里的sum值>0，即SQL语句在数据库的查询结果为1，即可以查到
            if (sum > 0) {
                System.out.println("登录成功");
            }
            else {
                System.out.println("登陆失败");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }

            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
