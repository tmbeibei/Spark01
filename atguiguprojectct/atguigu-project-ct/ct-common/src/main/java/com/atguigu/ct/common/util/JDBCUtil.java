package com.atguigu.ct.common.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author tianmin
 * @date 2020/2/24 0024
 * @notes
 */
public class JDBCUtil {
    private static final String MYSQL_DRIVER_CLASS = "com.mysql.jdbc.Driver";
    private static final String MYSQL_URL = "jdbc:mysql://hadoop101:3306/ctdemo?useUnicode=true&characterEncoding=UTF-8";
    private static final String MYSQL_USERNAME = "root";
    private static final String MYSQL_PASSWORD = "tianmin";

    /**
     * 获取数据库连接
     * @return
     */
    public static Connection getConnection(){
        Connection connection = null;
        try {
            Class.forName(MYSQL_DRIVER_CLASS);
            connection = DriverManager.getConnection(MYSQL_URL, MYSQL_USERNAME, MYSQL_PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return connection;
    }
}
