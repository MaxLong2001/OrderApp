package backend;

import backend.AppException.AppException;
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


        Customer customer = null;
        Owner owner = null;

        // ≤‚ ‘Constraint
        try {
            customer = new Customer("test_customer", "12");
        }catch (AppException e){
            System.out.println(e);
        }

        try {
            owner = new Owner("test_owner", "");
        }catch (AppException e){
            System.out.println(e);
        }

        customer.SetOwner(owner);

        List<Dish> dishes = Database.getDishList(owner.name);

        customer.CurrentUnfinished();

        customer.SetDish(dishes.get(0));

        /*try{
            customer.DelInOrder();
        }catch (AppException e){
            System.out.println(e);
        }*/

        try{
            customer.AddInOrder();
        }catch (AppException e){
            System.out.println(e);
        }

        try{
            customer.DelInOrder();
        }catch (AppException e){
            System.out.println(e);
        }

        customer.Submit();
    }
}
