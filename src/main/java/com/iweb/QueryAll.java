package com.iweb;

import com.iweb.domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class QueryAll {
    //解决SQL注入漏洞问题
    public static void main(String[] args) {
        //2、建立连接
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet resultSet = null;
        try {
            //2、建立连接
            conn = JDBCUtils.getConnection();
            //3、预编译SQl
            pstm = conn.prepareStatement("select * from t_user");

            resultSet = pstm.executeQuery();
            //多个用户是一个list集合，存放用户
            List<User> userList = new ArrayList<>();

            while(resultSet.next()){
                Integer id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String password = resultSet.getString("passward");
                Date date = resultSet.getDate("birthday");
                int age = resultSet.getInt("age");
                //每循环一次，就创建一个新用户
                User user = new User(id,name,password,date,age);
                userList.add(user);
                //循环输出
                //System.out.println(id+" "+name+" "+password+" "+date+" "+age+" ");
            }
            System.out.println(userList);

        } catch (SQLException e) {
            e.printStackTrace();
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
