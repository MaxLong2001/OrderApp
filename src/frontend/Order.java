package frontend;

import backend.Dish;

import javax.swing.*;

/**
 * 在顾客登录后并进入某个商家的界面后的界面。
 * 主要包含的内容：
 * 菜品列表
 * 评价界面
 */
public class Order extends MyView {

    public Order(){
        add(new Item(new Dish()));
        add(new Item(new Dish()));
        add(new Item(new Dish()));
        add(new Item(new Dish()));
        add(new Item(new Dish()));
    }
    /**
     * 菜品列表项。包括的内容有：菜品的名称、菜品的剩余数量、一个加号按钮。
     */
    class Item extends MyView {
        public Item(Dish dish){
            Box vBox = Box.createVerticalBox();
            vBox.add(new JLabel(dish.name));
            vBox.add(new JLabel("剩余数量：" + dish.remainQuantity));
            Box hBox = Box.createHorizontalBox();
            hBox.add(new JLabel("价格：" + dish.getPrice()));
//            setBounds(100, 100, 100, 100);
            JButton plus = new JButton("+");
//            plus.setSize( 1000, 10);
            hBox.add(plus);
            vBox.add(hBox);
            add(vBox);
        }

    }
}
