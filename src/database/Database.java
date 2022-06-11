package database;

import backend.Dish;
import backend.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @version 1.0
 * @author: 龙亿舟
 */
public class Database {

    /**
     * 数据库相关设置
     */
    static final String URL = "jdbc:mysql://rm-bp10c5dfstb3h6q96mo.mysql.rds.aliyuncs.com/order_app";
    static final String USER = "user";
    static final String PASS = "User_2021";
    static Connection conn;
    static Statement stmt;

    /**
     * 初始化数据库连接
     *
     * @throws ClassNotFoundException 数据库驱动类不存在异常
     * @throws SQLException           数据库连接异常
     */
    public static void init() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        System.out.println("Connecting to database...");
        conn = DriverManager.getConnection(URL, USER, PASS);
        System.out.println("Connected to database.");
    }

    /**
     * 添加用户
     * 根据方法传入的用户名将其加入数据库
     *
     * @param userName 用户名
     * @throws SQLException SQL异常
     */
    public static void insertCustomer(String userName) throws SQLException {
        String sql = "INSERT INTO customer (name) VALUES ('" + userName + "')";
        stmt = conn.createStatement();
        stmt.executeUpdate(sql);
        stmt.close();
    }

    /**
     * 添加商家
     * 根据方法传入的商家名和商家描述将其加入数据库
     *
     * @param ownerName   商家名
     * @param description 商家介绍
     * @throws SQLException SQL异常
     */
    public static void insertOwner(String ownerName, String description) throws SQLException {
        String sql = "INSERT INTO owner (name, description) VALUES ('" + ownerName + "', '" + description + "')";
        stmt = conn.createStatement();
        stmt.executeUpdate(sql);
        stmt.close();
    }

    /**
     * 添加菜品
     * 根据方法传入的菜品名、菜品介绍、菜品价格、菜品分类、菜品所属商家名将其加入数据库
     *
     * @param dishName     菜品名
     * @param introduction 菜品介绍
     * @param price        菜品价格
     * @param category     菜品类别
     * @param ownerName    菜品所属店家
     * @throws SQLException SQL异常
     */
    public static void insertDish(String dishName, String introduction, double price, int category, String ownerName) throws SQLException {
        String sqlFindOwnerId = "SELECT id FROM owner WHERE name = '" + ownerName + "'";
        stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sqlFindOwnerId);
        int ownerId = 0;
        while (rs.next()) {
            ownerId = rs.getInt("id");
        }
        rs.close();
        String sql = "INSERT INTO dish (name, introduction, price, category, owner_id) VALUES ('" + dishName + "', '" + introduction + "', " + price + ", " + category + ", '" + ownerId + "')";
        stmt.executeUpdate(sql);
        stmt.close();
    }

    /**
     * 添加或修改订单
     *
     * @param order 订单
     * @throws SQLException 数据库查询错误
     */
    //TODO: 添加或修改订单
    public static void insertOrder(Order order) throws SQLException {
        String customerName = order.getNameOfCustomer();
        String OwnerName = order.getNameOfOwner();
        double totalPrice = order.getPrice();
        HashMap<String, Integer> dishes = order.getDishes();
        String sqlFindCustomerId = "SELECT id FROM customer WHERE name = '" + customerName + "'";
        String sqlFindOwnerId = "SELECT id FROM owner WHERE name = '" + OwnerName + "'";
        stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sqlFindCustomerId);
        int customerId = 0;
        while (rs.next()) {
            customerId = rs.getInt("id");
        }
        rs.close();
        rs = stmt.executeQuery(sqlFindOwnerId);
        int ownerId = 0;
        while (rs.next()) {
            ownerId = rs.getInt("id");
        }
        rs.close();
        String sqlInsertOrders = "INSERT INTO orders (customer_id, owner_id, total) VALUES ('" + customerId + "', '" + ownerId + "', " + totalPrice + ")";
        stmt.executeUpdate(sqlInsertOrders);

        for (Map.Entry<String, Integer> stringIntegerEntry : dishes.entrySet()) {
            String dishName = (String) ((Map.Entry<?, ?>) stringIntegerEntry).getKey();
            int dishCount = (int) ((Map.Entry<?, ?>) stringIntegerEntry).getValue();
            String sqlFindDishId = "SELECT id FROM dish WHERE name = '" + dishName + "'";
            rs = stmt.executeQuery(sqlFindDishId);
            int dishId = 0;
            while (rs.next()) {
                dishId = rs.getInt("id");
            }
            rs.close();
            String sqlInsertOrderDishes = "INSERT INTO order_dish (orders_id, dish_id, amount) VALUES ((SELECT id FROM orders WHERE customer_id = '" + customerId + "' ORDER BY id DESC LIMIT 1), '" + dishId + "', " + dishCount + ")";
            stmt.executeUpdate(sqlInsertOrderDishes);
        }
    }


    /**
     * 根据用户名查询钱包余额
     *
     * @param userName 用户名
     * @return 钱包余额
     * @throws SQLException 数据库查询错误
     */
    public static double getWallet(String userName) throws SQLException {
        String sql = "SELECT wallet FROM customer WHERE name = '" + userName + "'";
        stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        double wallet = 0;
        while (rs.next()) {
            wallet = rs.getDouble("wallet");
        }
        rs.close();
        stmt.close();
        return wallet;
    }

    /**
     * 查询用户订单列表
     *
     * @param userName 用户名
     * @return 订单列表
     * @throws SQLException 数据库查询错误
     */
    public static List<Order> getUserOrderList(String userName) throws SQLException {
        String sql = "SELECT * FROM orders WHERE customer_id = (SELECT id FROM customer WHERE name = '" + userName + "')";
        stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        List<Order> orderList = new ArrayList<>();
        while (rs.next()) {
            Order order = new Order();
            order.setNameOfCustomer(userName);
            String sqlFindOwnerName = "SELECT name FROM owner WHERE id = '" + rs.getInt("owner_id") + "'";
            ResultSet rsFindOwnerName = stmt.executeQuery(sqlFindOwnerName);
            while (rsFindOwnerName.next()) {
                order.setNameOfOwner(rsFindOwnerName.getString("name"));
            }
            order.setPrice(rs.getDouble("total"));
            String sqlFindDishList = "SELECT dish_id, amount FROM order_dish WHERE orders_id = '" + rs.getInt("id") + "'";
            ResultSet rsFindDishList = stmt.executeQuery(sqlFindDishList);
            HashMap<String, Integer> dishList = new HashMap<>();
            while (rsFindDishList.next()) {
                String sqlFindDishName = "SELECT name FROM dish WHERE id = '" + rsFindDishList.getInt("dish_id") + "'";
                ResultSet rsFindDishName = stmt.executeQuery(sqlFindDishName);
                while (rsFindDishName.next()) {
                    dishList.put(rsFindDishName.getString("name"), rsFindDishList.getInt("amount"));
                }
            }
            order.setDishes(dishList);
            orderList.add(order);
        }
        rs.close();
        stmt.close();
        return orderList;
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
    public static void updateOwnerRating(String userName, String ownerName, double rating, String comment) throws
            SQLException {

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
