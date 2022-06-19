package frontend.Customer.home;

import backend.Customer;
import backend.User;
import frontend.Frontend;
import frontend.Tool.MyView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

/**
 * 顾客登陆后进入的界面，上面是顾客的基本信息，可以修改
 * 下面对于商家进行一个展示
 */
public class HomeView extends MyView {

    private Customer loginCustomer;

    private CustomerArea customerArea;
    private OwnerArea ownerArea;
    public HomeView(Customer loginCustomer){
        loginCustomer = Frontend.getLoginCustomer();

        ownerArea = new OwnerArea();
        customerArea = new CustomerArea(loginCustomer);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(customerArea);
        add(ownerArea);
        List<JPanel> ownerItems = ownerArea.ownerList.getItems();
        for(JPanel item : ownerItems){
            ((OwnerItem)item).enter.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispatchMyEvent(new OwnerArea.EnterOwner(((OwnerItem) item).owner));
                }
            });
        }
    }

}
