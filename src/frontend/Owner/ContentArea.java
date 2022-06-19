package frontend.Owner;

import backend.Dish;
import backend.Owner;

import javax.swing.*;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ContentArea extends JPanel {

    OrderList cookedOrderList;
    OrderList uncookedOrderList;
    DishList dishList;

    public ContentArea(Owner owner){

        JTabbedPane tabbedPane = new JTabbedPane();

        cookedOrderList = new OrderList(owner.ShowCooked());
        uncookedOrderList = new OrderList(owner.ShowUncooked());
        dishList = new DishList(owner.dishes);

        tabbedPane.addTab("未制作订单", uncookedOrderList);
        tabbedPane.addTab("已制作订单", cookedOrderList);
        tabbedPane.addTab("菜品管理", dishList);

        add(tabbedPane);

    }
}
