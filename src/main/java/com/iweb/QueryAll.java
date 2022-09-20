package com.iweb;

import java.sql.*;
import java.util.Scanner;

public class QueryDemo2 {
    //解决SQL注入漏洞问题
    public static void main(String[] args) {
        //2、建立连接
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet resultSet = null;
        try {
            conn = JDBCUtils.getConnection();
            //scanner能获取键盘上输入的字符串
            Scanner scanner = new Scanner(System.in);
            System.out.println("请输入用户名：");
            String name = scanner.nextLine();//获取键盘输入初值，放到name变量
            System.out.println("请输入密码：");
            String passward = scanner.nextLine();
            //输出输入的结果
            System.out.println(name+" "+passward);
            //3、预编译SQl
            pstm = conn.prepareStatement("SELECT COUNT(*) SUM FROM t_user WHERE name = ? AND passward = ?");
            pstm.setString(1,name);
            pstm.setString(2,passward);

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
