package database;

import java.sql.*;

public class Database {
    /**
     * �������ݿ�������Ϣ
     * ���ݱ�������޸�
     *
     * ��ɿ����ڷ������ϴ���ݿ�
     */
    static final String URL = "jdbc:mysql://rm-bp10c5dfstb3h6q96mo.mysql.rds.aliyuncs.com/order_app";
    static final String USER = "user";
    static final String PASS = "User_2021";
    static Connection conn;
    static Statement stmt;

    public static void init() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        System.out.println("Connecting to database...");
        conn = DriverManager.getConnection(URL, USER, PASS);
        System.out.println("Connected to database.");
    }

    public static void test_select() throws SQLException {
        stmt = conn.createStatement();
        String sql = "select id from test_table";
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            System.out.println(rs.getInt("id"));
        }
    }
}
