package com.iweb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class QueryAll {
    //解决SQL注入漏洞问题
    public static void main(String[] args) {
        //2、建立连接
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet resultSet = null;
        try {
            conn = JDBCUtils.getConnection();
            //3、预编译SQl
            pstm = conn.prepareStatement("select * from t_user");

            resultSet = pstm.executeQuery();

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
            JDBCUtils.closeResultSet(resultSet);
            //旧的写法，新的使用JDBC工具类
//            if (pstm != null) {
//                try {
//                    pstm.close();
//                } catch (SQLException e) {
//                    throw new RuntimeException(e);
//                }
//            }
            //新的写法
            JDBCUtils.closePstm(pstm);
            JDBCUtils.closeConn(conn);
        }
    }
}
