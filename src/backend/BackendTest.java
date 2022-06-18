package backend;

import backend.AppException.AppException;
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

    public static void main(String[] args) throws SQLException, ClassNotFoundException{

        Database.init();

        /*List<Owner> owners = null;
        try{
            owners = SearchAll.MySearch("new", Database.getAllOwner());
        }catch (AppException e){
            System.out.println(e);
        }
        assert owners != null;
        print(owners);*/

        List<Owner> owners = null;
        try {
            owners = ForCustomer.OwnerRecommend(Database.getAllOwner());
        }catch (AppException e){
            System.out.println(e);
        }

        assert false;
        print(owners);
    }
}
