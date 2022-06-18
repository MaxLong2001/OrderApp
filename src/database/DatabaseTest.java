package database;

import backend.AppException.AppException;
import backend.Dish;
import backend.Order;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public class DatabaseTest {
    public static void main(String[] args) throws SQLException, ClassNotFoundException, SQLException, AppException {
        System.out.println("Hello World!");

        Database.init();
        try {
            java.util.Date date = new java.util.Date(2022 - 1900, 0, 1, 12, 30, 30);
            HashMap<String, Integer> map = new HashMap<>();
            Order order = new Order();
            order.setNameOfCustomer("Customer1");
            order.setNameOfOwner("Owner");
            order.setOrderTime(date);
            order.setPrice(1000.0);
            order.setCooked(false);
            order.setCompleted(false);
            map.put("test", 100);
            map.put("new_dish", 100);
            order.setDishes(map);
            Database.deleteOrder(order);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
