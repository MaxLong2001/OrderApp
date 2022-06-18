package database;

import backend.AppException.AppException;
import backend.Dish;
import backend.Order;
import backend.Owner;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 龙亿舟
 * @version 1.0
 */
public class Database {

    /**
     * 数据库相关设置
     * 在这里我们设置了数据库的URL、用户名、密码
     */
    static final String URL = "jdbc:mysql://rm-bp10c5dfstb3h6q96mo.mysql.rds.aliyuncs.com/order_app";
    static final String USER = "user";
    static final String PASS = "User_2021";
    static Connection conn;
    static Statement stmt;

    /**
     * 初始化数据库连接
     * 设置数据库驱动程序并创建数据库连接
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
     * 根据传入的用户名和密码将其添加入数据库
     *
     * @param userName 用户名
     * @throws SQLException SQL异常
     */
    public static void insertCustomer(String userName, String password) throws SQLException, AppException {
        String sqlFindCustomer = "SELECT * FROM customer WHERE name = '" + userName + "'";
        String sqlFromOwner = "SELECT * FROM owner WHERE name = '" + userName + "'";
        boolean isCustomer = false;
        boolean isOwner = false;
        stmt = conn.createStatement();
        ResultSet rsFromCustomer = stmt.executeQuery(sqlFindCustomer);
        if (rsFromCustomer.next()) {
            isCustomer = true;
        }
        ResultSet rsFromOwner = stmt.executeQuery(sqlFromOwner);
        if (rsFromOwner.next()) {
            isOwner = true;
        }
        if (isCustomer || isOwner) {
            throw new AppException("用户名已存在");
        }
        rsFromCustomer.close();
        rsFromOwner.close();

        String sql = "INSERT INTO customer (name, password) VALUES ('" + userName + "', '" + password + "')";
        stmt.executeUpdate(sql);
        stmt.close();
    }

    /**
     * 添加商家
     * 根据传入的商家名和商家描述将其添加入数据库
     *
     * @param ownerName    商家名
     * @param introduction 商家介绍
     * @param password     商家密码
     * @throws SQLException SQL异常
     */
    public static void insertOwner(String ownerName, String introduction, String password) throws SQLException, AppException {
        String sqlFromCustomer = "SELECT * FROM customer WHERE name = '" + ownerName + "'";
        String sqlFromOwner = "SELECT * FROM owner WHERE name = '" + ownerName + "'";
        boolean isCustomer = false;
        boolean isOwner = false;
        stmt = conn.createStatement();
        ResultSet rsFromCustomer = stmt.executeQuery(sqlFromCustomer);
        if (rsFromCustomer.next()) {
            isCustomer = true;
        }
        ResultSet rsFromOwner = stmt.executeQuery(sqlFromOwner);
        if (rsFromOwner.next()) {
            isOwner = true;
        }
        if (isCustomer || isOwner) {
            throw new AppException("用户名已存在");
        }
        rsFromCustomer.close();
        rsFromOwner.close();

        String sql = "INSERT INTO owner (name, introduction, password) VALUES ('" + ownerName + "', '" + introduction + "', '" + password + "')";
        stmt.executeUpdate(sql);
        stmt.close();
    }

    /**
     * 根据用户名返回密码
     * 根据传入的用户名在顾客表和商家表中分别查找密码
     * 如果找到则返回用户身份与密码的键值对，否则抛出异常
     *
     * @param userName 用户名
     * @return 用户对应的密码
     * @throws SQLException SQL异常
     * @throws AppException 通用异常
     */
    public static Map<String, String> getPassword(String userName) throws SQLException, AppException {
        String sqlFromCustomer = "SELECT password FROM customer WHERE name = '" + userName + "'";
        String sqlFromOwner = "SELECT password FROM owner WHERE name = '" + userName + "'";
        String password;
        Map<String, String> map = new HashMap<>();
        stmt = conn.createStatement();
        ResultSet rsFromCustomer = stmt.executeQuery(sqlFromCustomer);
        if (rsFromCustomer.next()) {
            password = rsFromCustomer.getString("password");
            map.put("customer", userName);
            map.put("password", password);
            stmt.close();
            rsFromCustomer.close();
            return map;
        }
        ResultSet rsFromOwner = stmt.executeQuery(sqlFromOwner);
        if (rsFromOwner.next()) {
            password = rsFromOwner.getString("password");
            map.put("owner", userName);
            map.put("password", password);
            stmt.close();
            rsFromOwner.close();
            return map;
        }

        throw new AppException("用户名不存在");
    }

    /**
     * 添加菜品
     * 根据传入的菜品对象和菜品所属商家名将其添加入数据库
     * 在实现过程中，方法将根据传入的商家名自动检索其在数据库中的id，并将其作为外键与菜品表的owner_id字段关联
     *
     * @param dish      菜品对象
     * @param ownerName 商家名
     * @throws SQLException SQL异常
     */
    public static void insertDish(Dish dish, String ownerName) throws SQLException {
        String dishName = dish.getName();
        String introduction = dish.getIntroduction();
        double price = dish.getPrice();
        int sales = dish.getSalesQuantity();
        int remain = dish.getRemainQuantity();
        String type = dish.getType();
        String sqlFindOwnerId = "SELECT id FROM owner WHERE name = '" + ownerName + "'";
        stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sqlFindOwnerId);
        int ownerId = 0;
        while (rs.next()) {
            ownerId = rs.getInt("id");
        }
        rs.close();
        String sql = "INSERT INTO dish(name, introduction, price, sales, remain, type, owner_id) VALUES ('" + dishName + "', '" + introduction + "', " + price + ", " + sales + ", " + remain + ", '" + type + "', " + ownerId + ")";
        stmt.executeUpdate(sql);
        stmt.close();
    }

    /**
     * 添加或修改订单
     * 根据传入的订单对象和订单所属顾客名将其添加或修改入数据库
     * 如果订单已存在，则将其修改为传入的订单对象，否则将其添加入数据库
     * 在实现过程中，方法将根据传入的顾客名自动检索其在数据库中的id，并将其作为外键与订单表的customer_id字段关联
     *
     * @param order 订单
     * @throws SQLException 数据库查询错误
     */
    public static void insertOrder(Order order) throws SQLException {
        String customerName = order.getNameOfCustomer();
        String OwnerName = order.getNameOfOwner();
        double totalPrice = order.getPrice();
        boolean completed = order.isCompleted();
        java.util.Date orderTime = order.getOrderTime();
        HashMap<String, Integer> dishes = order.getDishes();

        String sqlFindThisOrder = "SELECT id FROM orders WHERE customer_id IN (SELECT id FROM customer WHERE name = '" + customerName + "' AND completed = false ORDER BY id DESC LIMIT 1)";
        stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sqlFindThisOrder);

        if (rs.next()) {
            int orderId = rs.getInt("id");

            String sqlUpdateTotal = "UPDATE orders SET total = " + totalPrice + " WHERE id = " + orderId;
            stmt.executeUpdate(sqlUpdateTotal);

            String sqlUpdateCompleted = "UPDATE orders SET completed = " + completed + " WHERE id = " + orderId;
            stmt.executeUpdate(sqlUpdateCompleted);

            String sqlUpdateDate = "UPDATE orders SET order_time = '" + orderTime + "' WHERE id = " + orderId;
            stmt.executeUpdate(sqlUpdateDate);

            String deleteOrderDish = "DELETE FROM order_dish WHERE orders_id = " + orderId;
            stmt.executeUpdate(deleteOrderDish);

            for (Map.Entry<String, Integer> entry : dishes.entrySet()) {
                String dishName = entry.getKey();
                int dishAmount = entry.getValue();

                String sqlFindDishId = "SELECT id FROM dish WHERE name = '" + dishName + "'";
                ResultSet rs2 = stmt.executeQuery(sqlFindDishId);
                int dishId = 0;
                while (rs2.next()) {
                    dishId = rs2.getInt("id");
                }
                rs2.close();

                String sqlInsertDish = "INSERT INTO order_dish (orders_id, dish_id, amount) VALUES (" + orderId + ", " + dishId + ", " + dishAmount + ")";
                stmt.executeUpdate(sqlInsertDish);
            }
        } else {
            String sqlFindCustomerId = "SELECT id FROM customer WHERE name = '" + customerName + "'";
            String sqlFindOwnerId = "SELECT id FROM owner WHERE name = '" + OwnerName + "'";
            stmt = conn.createStatement();

            rs = stmt.executeQuery(sqlFindCustomerId);
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

            String sqlInsertOrders = "INSERT INTO orders (customer_id, owner_id, total, completed, order_time) VALUES (" + customerId + ", " + ownerId + ", " + totalPrice + ", " + completed + ", '" + orderTime + "')";
            stmt.executeUpdate(sqlInsertOrders);

            for (Map.Entry<String, Integer> entry : dishes.entrySet()) {
                String dishName = entry.getKey();
                int dishAmount = entry.getValue();

                String sqlFindDishId = "SELECT id FROM dish WHERE name = '" + dishName + "'";
                rs = stmt.executeQuery(sqlFindDishId);
                int dishId = 0;
                while (rs.next()) {
                    dishId = rs.getInt("id");
                }
                rs.close();

                String sqlInsertOrderDishes = "INSERT INTO order_dish (orders_id, dish_id, amount) VALUES ((SELECT id FROM orders WHERE customer_id = '" + customerId + "' ORDER BY id DESC LIMIT 1), '" + dishId + "', '" + dishAmount + "')";
                stmt.executeUpdate(sqlInsertOrderDishes);
            }
        }
        stmt.close();
        rs.close();
    }


    /**
     * 根据用户名查询钱包余额
     * 根据传入的用户名查询数据库中的钱包余额，并返回该钱包余额
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
     * 更新钱包余额
     * 根据传入的用户名将其钱包余额更新为传入的钱包余额
     *
     * @param userName 用户名
     * @param amount   金额
     * @throws SQLException 数据库查询错误
     */
    public static void updateWallet(String userName, double amount) throws SQLException {
        String sql = "UPDATE customer SET wallet = " + amount + " WHERE name = '" + userName + "'";
        stmt = conn.createStatement();
        stmt.executeUpdate(sql);
        stmt.close();
    }

    /**
     * 修改顾客的用户名
     * 根据传入的用户名和新的用户名修改顾客数据库中的用户名
     * 方法将先检查新用户名是否已经存在
     *
     * @param oldName 旧用户名
     * @param newName 新用户名
     * @throws AppException 新用户名已存在
     * @throws SQLException 数据库查询错误
     */
    public static void changeCustomerName(String oldName, String newName) throws SQLException, AppException {
        String sqlCheckFromCustomer = "SELECT name FROM customer WHERE name = '" + newName + "'";
        String sqlCheckFromOwner = "SELECT name FROM owner WHERE name = '" + newName + "'";
        ResultSet rsCustomer = stmt.executeQuery(sqlCheckFromCustomer);
        ResultSet rsOwner = stmt.executeQuery(sqlCheckFromOwner);
        if (rsCustomer.next() || rsOwner.next()) {
            throw new AppException("新用户名已存在");
        }
        rsCustomer.close();
        rsOwner.close();

        String sql = "UPDATE customer SET name = '" + newName + "' WHERE name = '" + oldName + "'";
        stmt = conn.createStatement();
        stmt.executeUpdate(sql);
        stmt.close();
    }

    /**
     * 修改顾客的密码
     * 根据传入的用户名和新的密码修改顾客数据库中的密码
     *
     * @param userName 用户名
     * @param password 密码
     * @throws SQLException 数据库查询错误
     */
    public static void changeCustomerPassword(String userName, String password) throws SQLException {
        String sql = "UPDATE customer SET password = '" + password + "' WHERE name = '" + userName + "'";
        stmt = conn.createStatement();
        stmt.executeUpdate(sql);
        stmt.close();
    }

    /**
     * 修改商家用户名
     * 根据传入的用户名和新的用户名修改商家数据库中的用户名
     * 方法将先检查新用户名是否已经存在
     *
     * @param oldName 旧用户名
     * @param newName 新用户名
     * @throws AppException 新用户名已存在
     * @throws SQLException 数据库查询错误
     */
    public static void changeOwnerName(String oldName, String newName) throws AppException, SQLException {
        String sqlCheckFromCustomer = "SELECT name FROM customer WHERE name = '" + newName + "'";
        String sqlCheckFromOwner = "SELECT name FROM owner WHERE name = '" + newName + "'";
        ResultSet rsCustomer = stmt.executeQuery(sqlCheckFromCustomer);
        ResultSet rsOwner = stmt.executeQuery(sqlCheckFromOwner);
        if (rsCustomer.next() || rsOwner.next()) {
            throw new AppException("新用户名已存在");
        }
        rsCustomer.close();
        rsOwner.close();

        String sql = "UPDATE owner SET name = '" + newName + "' WHERE name = '" + oldName + "'";
        stmt = conn.createStatement();
        stmt.executeUpdate(sql);
        stmt.close();
    }

    /**
     * 修改商家用户密码
     * 根据传入的用户名和新的用户密码修改商家数据库中的用户密码
     *
     * @param userName 用户名
     * @param password 密码
     * @throws SQLException 数据库查询错误
     */
    public static void changeOwnerPassword(String userName, String password) throws SQLException {
        String sql = "UPDATE owner SET password = '" + password + "' WHERE name = '" + userName + "'";
        stmt = conn.createStatement();
        stmt.executeUpdate(sql);
        stmt.close();
    }

    /**
     * 修改商家简介
     * 根据传入的用户名和新的简介修改商家数据库中的简介
     *
     * @param userName     用户名
     * @param introduction 简介
     * @throws SQLException 数据库查询错误
     */
    public static void changeOwnerIntroduction(String userName, String introduction) throws SQLException {
        String sql = "UPDATE owner SET introduction = '" + introduction + "' WHERE name = '" + userName + "'";
        stmt = conn.createStatement();
        stmt.executeUpdate(sql);
        stmt.close();
    }

    /**
     * 查询用户订单列表
     * 根据传入的顾客名查询数据库中的订单列表，并返回该订单列表
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
            order.setCompleted(rs.getBoolean("completed"));
            order.setCooked(rs.getBoolean("cooked"));
            order.setOrderTime(rs.getDate("order_time"));

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
     * 获取订单列表
     * 根据传入的商家名查询数据库中的订单列表，并返回该订单列表
     *
     * @param ownerName 商家名
     * @return 订单列表
     * @throws SQLException 数据库查询错误
     */
    public static List<Order> getOwnerOrderList(String ownerName) throws SQLException {
        String sql = "SELECT * FROM orders WHERE owner_id = (SELECT id FROM owner WHERE name = '" + ownerName + "')";
        stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        List<Order> orderList = new ArrayList<>();
        while (rs.next()) {
            Order order = new Order();

            order.setOrderTime(rs.getDate("order_time"));
            order.setNameOfCustomer(rs.getString("customer_name"));
            order.setNameOfOwner(rs.getString("owner_name"));
            order.setPrice(rs.getDouble("total_price"));
            order.setCompleted(rs.getBoolean("completed"));
            order.setCooked(rs.getBoolean("cooked"));

            String sqlFindDishList = "SELECT * FROM order_dish WHERE orders_id = '" + rs.getInt("id") + "'";
            ResultSet rsFindDishList = stmt.executeQuery(sqlFindDishList);
            HashMap<String, Integer> dishes = new HashMap<>();
            while (rsFindDishList.next()) {
                String dishId = rsFindDishList.getString("dish_id");
                int quantity = rsFindDishList.getInt("quantity");
                String sqlGetDishName = "SELECT name FROM dish WHERE id = '" + dishId + "'";
                ResultSet rsGetDishName = stmt.executeQuery(sqlGetDishName);
                String dishName = "";
                while (rsGetDishName.next()) {
                    dishName = rsGetDishName.getString("name");
                }
                rsGetDishName.close();
                dishes.put(dishName, quantity);
            }
            rsFindDishList.close();
            order.setDishes(dishes);
            orderList.add(order);
        }
        rs.close();
        stmt.close();
        return orderList;
    }

    /**
     * 删除订单
     * 根据传入的订单对象删除数据库中的订单
     * 在实现过程中，将根据订单完成时间查找对应的订单记录，并删除
     *
     * @param order 订单
     * @throws SQLException 数据库查询错误
     */
    public static void deleteOrder(Order order) throws SQLException {
        String sql = "DELETE FROM orders WHERE order_time = '" + order.getOrderTime() + "'";
        stmt = conn.createStatement();
        stmt.executeUpdate(sql);
        stmt.close();
    }

    /**
     * 查询所有商家
     * 查询数据库中的所有商家，并返回该商家列表
     *
     * @return 商家列表
     * @throws SQLException 数据库查询错误
     */
    public static List<Owner> getAllOwner() throws SQLException {
        String sql = "SELECT * FROM owner";
        stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        List<Owner> ownerList = new ArrayList<>();
        while (rs.next()) {
            Owner owner = new Owner();
            owner.setName(rs.getString("name"));
            owner.setIntroduction(rs.getString("introduction"));
            owner.setRating(rs.getDouble("rating"));
            owner.setVisit(rs.getInt("visit"));
            ownerList.add(owner);
        }
        rs.close();
        stmt.close();
        return ownerList;
    }

    /**
     * 查询特定商家
     *
     * @param ownerName 商家名
     * @return 商家
     * @throws AppException 商家不存在
     * @throws SQLException 数据库查询错误
     */
    public static Owner getOwner(String ownerName) throws AppException, SQLException {
        String sql = "SELECT * FROM owner WHERE name = '" + ownerName + "'";
        stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        if (rs.next()) {
            Owner owner = new Owner();
            owner.setName(rs.getString("name"));
            owner.setIntroduction(rs.getString("introduction"));
            owner.setRating(rs.getDouble("rating"));
            owner.setVisit(rs.getInt("visit"));
            rs.close();
            stmt.close();
            return owner;
        } else {
            rs.close();
            stmt.close();
            throw new AppException("商家不存在");
        }
    }

    /**
     * 更新商家评分
     * 根据传入的商家名查询数据库中的商家记录，并添加传入的评分和评论
     * 在实现过程中，将会自动计算并更新商家的评分
     *
     * @param userName  用户名
     * @param ownerName 商家名
     * @param rating    评分
     * @param comment   评论
     * @throws SQLException 数据库查询错误
     */
    public static void updateOwnerRating(String userName, String ownerName, double rating, String comment) throws SQLException {
        String sqlAddComment = "INSERT INTO comments (customer_id, owner_id, rating, content) VALUES ((SELECT id FROM customer WHERE name = '" + userName + "'), (SELECT id FROM owner WHERE name = '" + ownerName + "'), '" + rating + "', '" + comment + "')";
        stmt = conn.createStatement();
        stmt.executeUpdate(sqlAddComment);

        double totalRating = 0;
        int count = 0;
        String sqlFindRating = "SELECT rating FROM comments WHERE owner_id = (SELECT id FROM owner WHERE name = '" + ownerName + "')";
        ResultSet rsFindRating = stmt.executeQuery(sqlFindRating);
        while (rsFindRating.next()) {
            totalRating += rsFindRating.getDouble("rating");
            count++;
        }
        rsFindRating.close();

        double averageRating = totalRating / count;
        String sqlUpdateRating = "UPDATE owner SET rating = '" + averageRating + "' WHERE name = '" + ownerName + "'";
        stmt.executeUpdate(sqlUpdateRating);

        stmt.close();
    }

    /**
     * 查询商家评分
     * 根据传入的商家名查询数据库中的商家记录，并返回该商家的评分
     *
     * @param ownerName 商家名
     * @return 商家评分
     * @throws SQLException 数据库查询错误
     */
    public static double getOwnerRating(String ownerName) throws SQLException {
        String sql = "SELECT rating FROM owner WHERE name = '" + ownerName + "'";
        stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        double rating = 0;
        while (rs.next()) {
            rating = rs.getDouble("rating");
        }
        rs.close();
        stmt.close();
        return rating;
    }

    /**
     * 访问商家
     * 根据传入的商家名进行访问商家操作，将其访问量加1
     *
     * @param ownerName 商家名
     * @throws SQLException 数据库查询错误
     */
    public static void visitOwner(String ownerName) throws SQLException {
        String sql = "UPDATE owner SET visit = visit + 1 WHERE name = '" + ownerName + "'";
        stmt = conn.createStatement();
        stmt.executeUpdate(sql);
        stmt.close();
    }

    /**
     * 获取菜品列表
     * 根据传入的商家名查询数据库中的商家记录，并返回该商家的菜品列表
     *
     * @param ownerName 商家名
     * @return 菜品列表
     * @throws SQLException 数据库查询错误
     */
    public static List<Dish> getDishList(String ownerName) throws SQLException {
        String sql = "SELECT * FROM dish WHERE owner_id = (SELECT id FROM owner WHERE name = '" + ownerName + "')";
        stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        List<Dish> dishList = new ArrayList<>();
        while (rs.next()) {
            Dish dish = new Dish();

            dish.setName(rs.getString("name"));
            dish.setPrice(rs.getDouble("price"));
            dish.setIntroduction(rs.getString("introduction"));
            dish.setSalesQuantity(rs.getInt("sales"));
            dish.setRemainQuantity(rs.getInt("remain"));
            dish.setType(rs.getString("type"));

            dishList.add(dish);
        }
        rs.close();
        stmt.close();
        return dishList;
    }

    /**
     * 获取菜品详细信息
     * 根据传入的菜品名查询数据库中的菜品记录，并返回该菜品的详细信息
     *
     * @param dishName 菜品名
     * @return 菜品详细信息
     * @throws SQLException 数据库查询错误
     */
    public static Dish getDish(String dishName) throws SQLException {
        String sql = "SELECT * FROM dish WHERE name = '" + dishName + "'";
        stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        Dish dish = new Dish();
        while (rs.next()) {
            dish.setName(rs.getString("name"));
            dish.setPrice(rs.getDouble("price"));
            dish.setIntroduction(rs.getString("introduction"));
            dish.setSalesQuantity(rs.getInt("sales"));
            dish.setRemainQuantity(rs.getInt("remain"));
            dish.setType(rs.getString("type"));
        }
        rs.close();
        stmt.close();
        return dish;
    }

    /**
     * 商家修改菜品
     * 根据传入的商家名和菜品对象查询数据库中的菜品记录，并修改该菜品的信息
     *
     * @param ownerName 商家名
     * @param dish      菜品
     * @throws SQLException 数据库查询错误
     */
    public static void changeDish(String ownerName, Dish dish) throws SQLException {
        String sql = "UPDATE dish SET name = '" + dish.getName() + "', price = '" + dish.getPrice() + "', introduction = '" + dish.getIntroduction() + "', type = '" + dish.getType() + "', sales = '" + dish.getSalesQuantity() + "', remain = '" + dish.getRemainQuantity() + "' WHERE owner_id = (SELECT id FROM owner WHERE name = '" + ownerName + "') AND name = '" + dish.getName() + "'";
        stmt = conn.createStatement();
        stmt.executeUpdate(sql);
        stmt.close();
    }

    /**
     * 商家删除菜品
     * 根据传入的商家名和菜品对象查询数据库中的菜品记录，并删除该菜品
     *
     * @param ownerName 商家名
     * @param dish      菜品
     * @throws SQLException 数据库查询错误
     */
    public static void deleteDish(String ownerName, Dish dish) throws SQLException {
        String sql = "DELETE FROM dish WHERE owner_id = (SELECT id FROM owner WHERE name = '" + ownerName + "') AND name = '" + dish.getName() + "'";
        stmt = conn.createStatement();
        stmt.executeUpdate(sql);
        stmt.close();
    }

    /**
     * 测试用方法
     *
     * @throws SQLException 数据库查询错误
     */
    public static void test_select() throws SQLException {
        stmt = conn.createStatement();
        String sql = "select id from test_table";
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            System.out.println(rs.getInt("id"));
        }
    }
}
