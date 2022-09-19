package com.iweb;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.druid.pool.DruidPooledConnection;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

import static com.iweb.JDBCUtils.*;

public class DruidDataSouceDemo {
    public static void main(String[] args) throws Exception {
        //读取properties文件
        Properties p = new Properties();
        InputStream inputStream = DruidDataSouceDemo.class.getClassLoader().getResourceAsStream("druidjdbc.properties");
        p.load(inputStream);
        //建立数据源的第一种方式(使用工厂方式,必须读取properties文件)
        //通过Druid的DataSource(数据源的意思)的工厂Factory去建立一个数据源
        DataSource dataSource = DruidDataSourceFactory.createDataSource(p);
        //数据源创建连接
        Connection connection = dataSource.getConnection();
        System.out.println(dataSource);
        //打印出来的结果
        /**
         * CreateTime:"2022-09-17 00:06:30", 打印出来的时间
         * ActiveCount:0, 现在有几个活动
         * PoolingCount:0, 池子里总共有几个
         * CreateCount:0, 创建几个
         * DestroyCount:0, 销毁几个
         * CloseCount:0, 关闭几个
         * ConnectCount:0,
         * Connections:[]
         */
        //建立数据源的第二种方法(普通方法,无需读取properties文件)
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName(driverClassName);
        druidDataSource.setUrl(url);
        druidDataSource.setUsername(username);
        druidDataSource.setPassword("123456");
        //数据源创建连接
        DruidPooledConnection connection1 = druidDataSource.getConnection();
        System.out.println(druidDataSource);
    }
}
