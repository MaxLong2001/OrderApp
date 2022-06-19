package frontend.Tool;

import backend.Dish;
import backend.Order;
import frontend.DishItem;
import frontend.Frontend;
import frontend.ModifyDish;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

/**
 * 商家的菜品清单，有分类选项卡
 */
public class DishArea extends JPanel {
    public MyButton newDish;

    JTabbedPane tabbedPane;
    public Map<String, DishList> dishListMap = new HashMap<>();


    public DishList dishList;


    public DishArea(Map<String, List<Dish>> dishMap, Order order) {


        tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
        Set<Map.Entry<String, List<Dish>>> dishSet = dishMap.entrySet();
        for(Map.Entry<String, List<Dish>> dishType : dishSet){
            dishList = new DishList(dishType.getValue(), order);
            dishListMap.put(dishType.getKey(), dishList);
            tabbedPane.addTab(dishType.getKey(), dishList);
            //向标签中嵌入组件
//            JPanel tab = new JPanel();
//            tab.add(new JLabel(dishType.getKey()));
//            tabbedPane.setTabComponentAt(0, tab);
        }

        add(tabbedPane);
    }
    // 给商家用
    public DishArea(Map<String, List<Dish>> dishMap) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        newDish = new MyButton("添加菜品");

        tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
        Set<Map.Entry<String, List<Dish>>> dishSet = dishMap.entrySet();
        for(Map.Entry<String, List<Dish>> dishType : dishSet){
            dishList = new DishList(dishType.getValue());
            dishListMap.put(dishType.getKey(), dishList);
            tabbedPane.addTab(dishType.getKey(), dishList);
            //向标签中嵌入组件
//            JPanel tab = new JPanel();
//            tab.add(new JLabel(dishType.getKey()));
//            tabbedPane.setTabComponentAt(0, tab);
        }

        add(newDish);
        add(tabbedPane);

        newDish.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MyView.openWindow(new ModifyDish(Frontend.getLoginOwner()), "新增菜品");
            }
        });
    }
}