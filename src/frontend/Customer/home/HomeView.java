package frontend.Customer.home;

import backend.Customer;
import backend.User;
import frontend.Tool.MyView;

import javax.swing.*;

/**
 * 顾客登陆后进入的界面，上面是顾客的基本信息，可以修改
 * 下面对于商家进行一个展示
 */
public class HomeView extends MyView {

    private Customer loginCustomer;

    private CustomerArea customerArea;
    private OwnerArea ownerArea;
    public HomeView(Customer loginCustomer){


        ownerArea = new OwnerArea();
        customerArea = new CustomerArea(loginCustomer);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(customerArea);
        add(ownerArea);
    }

}
