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
//            HashMap<String, Integer> map = new HashMap<>();
//            Order order = new Order();
//            order.setNameOfCustomer("Customer1");
//            order.setNameOfOwner("Owner");
//            order.setOrderTime(date);
//            order.setPrice(1000.0);
//            order.setCooked(false);
//            order.setCompleted(false);
//            map.put("test", 100);
//            map.put("new_dish", 100);
//            order.setDishes(map);
//            Database.insertOrder(order);
//            System.out.println("Insert order successfully!");
            List<Order> orders = Database.getOwnerOrderList("Owner");
            for (Order order : orders) {
                System.out.println(order.getNameOfCustomer());
                System.out.println(order.getNameOfOwner());
                System.out.println(order.getOrderTime());
                System.out.println(order.getPrice());
                System.out.println(order.isCooked());
                System.out.println(order.isCompleted());
                HashMap<String, Integer> dishes = order.getDishes();
                for (String dish : dishes.keySet()) {
                    System.out.println(dish + " " + dishes.get(dish));
                }
                System.out.println("---------------------------------");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
