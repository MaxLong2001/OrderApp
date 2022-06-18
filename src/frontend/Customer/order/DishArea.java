package frontend.Customer.order;

import backend.Dish;
import backend.Order;
import frontend.DishItem;
import frontend.Frontend;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 商家的菜品清单，有分类选项卡
 */
public class DishArea extends JPanel {

    JTabbedPane tabbedPane;
    Map<String, DishList> dishListMap = new HashMap<>();

    public DishArea(Map<String, List<Dish>> dishMap, Order order) {

        tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
        Set<Map.Entry<String, List<Dish>>> dishSet = dishMap.entrySet();
        for(Map.Entry<String, List<Dish>> dishType : dishSet){
            DishList dishList = new DishList(dishType.getValue(), order);
            dishListMap.put(dishType.getKey(), dishList);
            tabbedPane.addTab(dishType.getKey(), dishList);
            //向标签中嵌入组件
//            JPanel tab = new JPanel();
//            tab.add(new JLabel(dishType.getKey()));
//            tabbedPane.setTabComponentAt(0, tab);
        }

        add(tabbedPane);
    }
}

/**
 * 已经存在滚动条
 */
class DishList extends JPanel{
    List<Dish> dishes;
    Order order;


    private int listWidth = 400;
    private int listHeight = 400;

    public DishList(List<Dish> dishes, Order order){
        this.dishes = dishes;
        this.order = order;


        Box vBox = Box.createVerticalBox();

        for(Dish d : dishes){
            DishItem dishItem;
            int orderedNum;
            if(order != null){
                if(order.dishes.get(d.getName()) == null)orderedNum = 0;
                else orderedNum = order.dishes.get(d.getName());
            }else{
                orderedNum = 0;
            }
            dishItem = new DishItem(DishItem.customerBrowse, d, orderedNum);
            vBox.add(dishItem);
        }

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setPreferredSize(new Dimension(listWidth + 40, listHeight));
        scrollPane.setViewportView(vBox);
        add(scrollPane);
    }
}