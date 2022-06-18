package database;

import backend.AppException.AppException;
import backend.Dish;

import java.sql.SQLException;
import java.util.List;

public class DatabaseTest {
    public static void main(String[] args) throws SQLException, ClassNotFoundException, SQLException, AppException {
        System.out.println("Hello World!");

        Database.init();
    }
}
