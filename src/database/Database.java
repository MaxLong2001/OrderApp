package database;

import java.sql.*;

public class Database {
    public static void init() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");

        /**
         * �������ݿ�������Ϣ
         */
        final String url = "jdbc:mysql://localhost:3306/order";
        final String user = "root";
        final String pass = "Longyizhou2001";

        System.out.println("Connecting to database...");
        Connection conn = DriverManager.getConnection(url, user, pass);
    }
}
