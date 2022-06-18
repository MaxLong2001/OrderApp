package backend;

import backend.AppException.AppException;
import backend.Monitor.Constraint;
import backend.Recommend.ForCustomer;
import database.Database;

import java.sql.SQLException;
import java.util.List;

public class BackendTest {

    public static void print(List<Owner> owners){

        for(Owner owner: owners){
            System.out.println(owner.name + " " + owner.rating + " " + owner.visit);
        }
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        Database.init();

        // ≤‚ ‘Constraint
        try {
            Constraint.CheckPwd("JASb_2");
        }catch (AppException e){
            System.out.println(e);
        }
    }
}
