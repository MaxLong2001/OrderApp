package database;

import backend.AppException.AppException;

import java.sql.SQLException;
import java.util.Map;

public class DatabaseTest {
    public static void main(String[] args) throws SQLException, ClassNotFoundException, SQLException, AppException {
        System.out.println("Hello World!");

        Database.init();
        try {
            Map<String, String> map = Database.getPassword("balabala");
            System.out.println(map);
        } catch (AppException e) {
            e.printStackTrace();
        }
    }
}
