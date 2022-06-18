package database;

import backend.AppException.AppException;
import backend.Order;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class DatabaseTest {
    public static void main(String[] args) throws SQLException, ClassNotFoundException, SQLException, AppException {
        System.out.println("Hello World!");

        Database.init();
        try {
            java.util.Date date = new java.util.Date("2022-01-01 00:00:00");
            Map<String, Integer> map = new HashMap<>();
            Order order = new Order();
            order.setNameOfCustomer("User1");
            order.setNameOfOwner("Owner1");
            order.setOrderTime(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
