package database;

import java.sql.*;

public class Database {
    public static void init() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");

        /**
         * 本地数据库连接信息
         * 根据本地情况修改
         *
         * 或可考虑在服务器上搭建数据库
         */
        final String url = "jdbc:mysql://localhost:3306/order";
        final String user = "root";
        final String pass = "Longyizhou2001";

        System.out.println("Connecting to database...");
        Connection conn = DriverManager.getConnection(url, user, pass);
    }
}
