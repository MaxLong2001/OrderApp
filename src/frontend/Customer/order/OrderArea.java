package frontend.Customer.order;

import backend.Dish;
import backend.Order;
import frontend.DishItem;
import frontend.Frontend;
import frontend.Tool.MyColor;
import frontend.Tool.MyItem;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 顾客的未完成订单内容清单，包括列表和提交按钮
 */
public class OrderArea extends JPanel {
    private Order order;

    private OrderItemList orderItemList;
    private Box orderCommit;

    public OrderArea(Order order){
        this.order = order;

        orderItemList = new OrderItemList(order);

        orderCommit = new OrderCommit(order);

        Box vBox = Box.createVerticalBox();
        vBox.add(orderItemList);
        vBox.add(orderCommit);
        add(vBox);
    }

}
class OrderCommit extends Box {

    public OrderCommit(Order order){
        super(BoxLayout.X_AXIS);

        JLabel total = new JLabel();
        total.setText("合计：￥" + order.getPrice());
        JPanel totalArea = new JPanel();
        totalArea.setLayout(new FlowLayout(FlowLayout.LEFT));
        totalArea.add(total);

        JButton commit = new JButton();
        commit.setText("提交订单");
        JPanel commitArea = new JPanel();
        commitArea.setLayout(new FlowLayout(FlowLayout.RIGHT));
        commitArea.add(commit);

        add(totalArea);
        add(commitArea);
    }
}

class OrderItemList extends JPanel{
    private int listWidth = 400;
    private int listHeight = 400;

    List<MyItem> orderItems = new ArrayList<>();

    public OrderItemList(Order order){
        Box vBox = Box.createVerticalBox();
        Set<Map.Entry<String, Integer>> entry = order.getDishes().entrySet();
        for(Map.Entry<String, Integer> i : entry){
            //todo
            Dish dish = new Dish();
            dish.setName(i.getKey());
            dish.setPrice(12.3);
            DishItem item = new DishItem(DishItem.customerOrder, dish ,i.getValue());
            orderItems.add(item);
            vBox.add(item);
        }

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setPreferredSize(new Dimension(listWidth + 40, listHeight));
        scrollPane.setViewportView(vBox);
        add(scrollPane);
    }
}
