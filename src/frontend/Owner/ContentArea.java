package frontend.Owner;

import backend.Dish;
import backend.Owner;

import javax.swing.*;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ContentArea extends JPanel {

    private OrderList cookedOrderList;
    private OrderList uncookedOrderList;
    private DishList dishList;

    public ContentArea(Owner owner){

        JTabbedPane tabbedPane = new JTabbedPane();
//        tabbedPane.addTab(, dishList);
            //向标签中嵌入组件
//            JPanel tab = new JPanel();
//            tab.add(new JLabel(dishType.getKey()));
//            tabbedPane.setTabComponentAt(0, tab);


        cookedOrderList = new OrderList(owner.ShowCooked());
        uncookedOrderList = new OrderList(owner.ShowUncooked());
        dishList = new DishList(owner.dishes);

        tabbedPane.addTab("未制作订单", uncookedOrderList);
        tabbedPane.addTab("已制作订单", cookedOrderList);
        tabbedPane.addTab("菜品管理", dishList);



        add(tabbedPane);

    }
}
