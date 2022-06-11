import database.Database;
import frontend.Frontend;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        System.out.println("Hello World!");
        new Frontend();

        Database.init();
    }
}
