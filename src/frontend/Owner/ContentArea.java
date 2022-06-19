package frontend.Owner;

import backend.AppException.AppException;
import backend.Dish;
import backend.Owner;
import frontend.Frontend;
import frontend.ModifyDish;
import frontend.Tool.DishArea;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.Timer;

public class ContentArea extends JPanel {

    OrderList cookedOrderList;
    OrderList uncookedOrderList;
    DishArea dishArea;

    JTabbedPane tabbedPane;
    Owner owner;

    public ContentArea(){
        owner = Frontend.getLoginOwner();

        tabbedPane = new JTabbedPane();

        cookedOrderList = new OrderList(owner.orders_cooked);
        uncookedOrderList = new OrderList(owner.orders_uncooked);

        try{
            dishArea = new DishArea(owner.showDishes(owner.getName()));
        }catch (AppException ex){
            ex.message(this);
        }

        tabbedPane.addTab("未制作订单", uncookedOrderList);
        tabbedPane.addTab("已制作订单", cookedOrderList);
        tabbedPane.addTab("菜品管理", dishArea);

        add(tabbedPane);


    }
    public  void refresh(){
        cookedOrderList.fresh();
        uncookedOrderList.fresh();
        //todo
        tabbedPane.removeTabAt(2);
        try{
            dishArea = new DishArea(owner.showDishes(owner.getName()));
        }catch (AppException ex){
            ex.message(this);
        }
        tabbedPane.addTab("菜品管理", dishArea);
    }
}
