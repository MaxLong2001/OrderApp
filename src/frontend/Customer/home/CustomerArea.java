package frontend.Customer.home;

import backend.Customer;
import frontend.Tool.MyButton;
import frontend.Tool.MyItem;

import javax.swing.*;
import java.awt.*;

public class CustomerArea extends MyItem {

    public CustomerArea(Customer customer) {
        getNameLabel().setText(customer.getName());
        closeIntroduction();

        MyButton modify = new MyButton("修改信息");
        modify.setPreferredSize(new Dimension(120, 30));

        addRight(modify);
    }
}
