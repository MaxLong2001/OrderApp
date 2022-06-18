package database;

import backend.AppException.AppException;
import backend.Order;

import java.sql.SQLException;
import java.util.HashMap;

public class DatabaseTest {
    public static void main(String[] args) throws SQLException, ClassNotFoundException, SQLException, AppException {
        System.out.println("Hello World!");

        Database.init();
        try {
//            java.util.Date date = new java.util.Date(2022 - 1900, 0, 1, 12, 0, 0);
//            HashMap<String, Integer> map = new HashMap<>();
//            Order order = new Order();
//            order.setNameOfCustomer("User1");
//            order.setNameOfOwner("Owner1");
//            order.setOrderTime(date);
//            order.setPrice(10.0);
//            order.setCooked(false);
//            order.setCompleted(false);
//            map.put("test", 100);
//            map.put("new_dish", 2);
//            order.setDishes(map);
//            Database.insertOrder(order);
//            System.out.println("Insert order successfully!");
            Database.changeOwnerPassword("Owner", "10203040");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
