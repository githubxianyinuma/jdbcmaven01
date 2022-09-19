package com.iweb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 对数据库表进行增加，删除，修改
 */
public class UpdateDemo {

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
            Class.forName(driverClassName);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        //2、连接数据库，DriverManager.getConnection往数据库写入用户信息，println输出代表已经连接
        Connection conn = null;//为了使用完后关闭数据库
        Statement statement = null;
        try {
            conn = DriverManager.getConnection(url, username, pw);
            //System.out.println(conn);
            //3、预备SQL语句
            //String insertSql = "insert t_user(name,passward,birthday,age) values('zhangsan','123456','2000-01-01',20)";//往数据库插入数据
            //String updateSql = "update t_user set name='zhangsanfeng' where id=1";//修改数据库
            String deleteSql = "delete from t_user where id = 1";
            statement = conn.createStatement();//statement是语句的意思
            //4、执行SQL语句
            //int i = statement.executeUpdate(insertSql);
            //System.out.println("插入"+i+"条数据");
            //int i = statement.executeUpdate(updateSql);
            //System.out.println("数据库变动"+i+"条数据");
            int i = statement.executeUpdate(deleteSql);
            System.out.println("数据库删除"+i+"条数据");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //finally里面的代码最终都会运行
        finally {
            if (statement!=null)
            {
                try {
                    statement.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (conn!=null)
            {
                try {
                    conn.close();//关闭数据库连接
                } catch (SQLException e) {
                    //throw new RuntimeException(e);
                    //什么都不用写
                }
            }
        }
    }
}
