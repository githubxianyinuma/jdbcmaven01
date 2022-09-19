package com.iweb;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * 封装JDBC工具类
 */

public class JDBCUtils {

    //定义属性
    static String url;
    static String username;
    static String pw;
    static String driverClassName;
    //静态代码块,优先执行
    static {
        //通过Properties类获取driverClassName
        Properties p = new Properties();
        //读取该资源文件jdbc.properties,将一些需要的资源单独写出来
        //通过类名的class对象先获取类名加载器getClassLoader,通过类加载器获取资源流getResourceAsStream
        InputStream inputStream = JDBCUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
        try {
            //这里已经将这个文件资源流读入Properties对象里面了
            p.load(inputStream);
            //通过对象获取里面的值,即通过get方法
            url = p.getProperty("url");
            username = p.getProperty("username");
            pw = p.getProperty("pw");
            driverClassName = p.getProperty("driverClassName");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //1、加载驱动
        try {
            Class.forName(driverClassName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    /**
     * 建立连接
     */
    //将建立连接封装成一个方法
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, pw);
    }
    /**
     * 关闭结果集
     */
    public static void closeResultSet(ResultSet resultSet){
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    /**
     * 关闭结果集
     */
    public static void closePstm(PreparedStatement pstm){
        if (pstm != null) {
            try {
                pstm.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    /**
     * 关闭连接
     */
    public static void closeConn(AutoCloseable conn){
        if (conn != null) {
            try {
                conn.close();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
