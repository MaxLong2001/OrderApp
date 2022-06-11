package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    /**
     * 本地数据库连接信息
     * 根据本地情况修改
     *
     * 或可考虑在服务器上搭建数据库
     */
    static final String URL = "jdbc:mysql://localhost:3306/order";
    static final String USER = "root";
    static final String PASS = "Longyizhou2001";
    static Connection conn;

    public static void init() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");

        System.out.println("Connecting to database...");
        conn = DriverManager.getConnection(URL, USER, PASS);
        System.out.println("Connected to database.");
    }
}
