package database;

import backend.Dish;
import backend.Order;

import java.sql.*;
import java.util.List;

public class Database {
    /**
     * 本地数据库连接信息
     * 根据本地情况修改
     * <p>
     * 或可考虑在服务器上搭建数据库
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

    /**
     * 根据用户名查询钱包余额
     *
     * @param userName 用户名
     * @return 钱包余额
     * @throws SQLException 数据库查询错误
     */
    public static double getWallet(String userName) throws SQLException {
        return 0;
    }

    /**
     * 查询用户订单列表
     *
     * @param userName 用户名
     * @return 订单列表
     * @throws SQLException 数据库查询错误
     */
    public static List<Order> getUserOrderList(String userName) throws SQLException {
        return null;
    }

    /**
     * 添加或修改订单
     *
     * @param order 订单
     * @throws SQLException 数据库查询错误
     */
    public static void addOrder(Order order) throws SQLException {

    }

    /**
     * 删除订单
     *
     * @param order 订单
     * @throws SQLException 数据库查询错误
     */
    public static void deleteOrder(Order order) throws SQLException {

    }

    /**
     * 更新钱包余额
     *
     * @param userName 用户名
     * @param amount   金额
     * @throws SQLException 数据库查询错误
     */
    public static void updateWallet(String userName, double amount) throws SQLException {

    }

    /**
     * 更新商家评分
     *
     * @param userName  用户名
     * @param ownerName 商家名
     * @param rating    评分
     * @param comment   评论
     * @throws SQLException 数据库查询错误
     */
    public static void updateOwnerRating(String userName, String ownerName, double rating, String comment) throws SQLException {

    }

    /**
     * 更新商家评分
     *
     * @param ownerName 商家名
     * @return 商家评分
     * @throws SQLException 数据库查询错误
     */
    public static double getOwnerRating(String ownerName) throws SQLException {
        return 0;
    }

    /**
     * 获取菜品列表
     *
     * @param ownerName 商家名
     * @return 菜品列表
     * @throws SQLException 数据库查询错误
     */
    public static List<Dish> getDishList(String ownerName) throws SQLException {
        return null;
    }

    /**
     * 获取订单列表
     *
     * @param ownerName 商家名
     * @return 订单列表
     * @throws SQLException 数据库查询错误
     */
    public static List<Order> getOwnerOrderList(String ownerName) throws SQLException {
        return null;
    }

    /**
     * 商家添加菜品
     *
     * @param ownerName 商家名
     * @param dish      菜品
     * @throws SQLException 数据库查询错误
     */
    public static void addDish(String ownerName, Dish dish) throws SQLException {

    }

    /**
     * 商家修改菜品
     *
     * @param ownerName 商家名
     * @param dish      菜品
     * @throws SQLException 数据库查询错误
     */
    public static void changeDish(String ownerName, Dish dish) throws SQLException {

    }

    /**
     * 商家删除菜品
     *
     * @param ownerName 商家名
     * @param dish      菜品
     * @throws SQLException 数据库查询错误
     */
    public static void deleteDish(String ownerName, Dish dish) throws SQLException {

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
