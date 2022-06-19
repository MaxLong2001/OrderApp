package frontend.Customer.order;

import backend.AppException.AppException;
import backend.Dish;
import backend.Order;
import frontend.DishItem;
import frontend.Frontend;
import frontend.Tool.MyButton;
import frontend.Tool.MyColor;
import frontend.Tool.MyItem;
import frontend.Tool.MyList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 顾客的未完成订单内容清单，包括列表和提交按钮
 */
public class OrderArea extends JPanel {

    private OrderItemList orderItemList;
    private OrderCommit orderCommit;

    Box vBox;
    public OrderArea(){
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        orderItemList = new OrderItemList();

        orderCommit = new OrderCommit();

        vBox = Box.createVerticalBox();
        add(orderItemList);
        add(orderCommit);
//        add(vBox);
    }

    public void refresh() {
        orderCommit.refresh();
        orderItemList.refresh();
        repaint();
    }
}
class OrderCommit extends Box {
    JLabel total;
    public OrderCommit(){
        super(BoxLayout.X_AXIS);

        total = new JLabel();
        if(Frontend.currentOrder == null) total.setText("合计：￥ 0");
        else total.setText("合计：￥" + Frontend.currentOrder.getPrice());
        JPanel totalArea = new JPanel();
        totalArea.setLayout(new FlowLayout(FlowLayout.LEFT));
        totalArea.add(total);

        MyButton commit = new MyButton("提交订单");
        commit.setPreferredSize(new Dimension(60, 30));
        JPanel commitArea = new JPanel();
        commitArea.setLayout(new FlowLayout(FlowLayout.RIGHT));
        commitArea.add(commit);

        add(totalArea);
        add(commitArea);

        commit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    throw new AppException(Frontend.getLoginCustomer().Submit());
                }catch (AppException ex){
                    ex.message(OrderCommit.this);
                }
            }
        });
    }
    public void refresh(){
        total.setText("合计：￥" + Frontend.currentOrder.getPrice());
        repaint();
    }
}

class OrderItemList extends MyList {

    List<DishItem> orderItems = new ArrayList<>();

    public OrderItemList(){
        if(Frontend.currentOrder != null){
            Set<Map.Entry<String, Integer>> entry = Frontend.currentOrder.getDishes().entrySet();
            for(Map.Entry<String, Integer> i : entry){
                Dish dish = new Dish();
                dish.setName(i.getKey());
                dish.setPrice(12.3);
                DishItem item = new DishItem(DishItem.customerOrder, dish ,i.getValue());
                orderItems.add(item);
                addItem(item);
            }
        }
        setHeight(400);
    }

    public void refresh() {
        if(Frontend.currentOrder != null){
            Set<Map.Entry<String, Integer>> entry = Frontend.currentOrder.getDishes().entrySet();
            for(Map.Entry<String, Integer> i : entry){
                int flag = 0;
                for(DishItem item : orderItems){
                    if(i.getKey().equals(item.dish.getName()) && (int)(i.getValue()) > item.orderdNum){
                        item.addOrderNum();
                        flag = 1;
                    }else if(i.getKey().equals(item.dish.getName()) && (int)(i.getValue()) == item.orderdNum){
                        flag = 2;
                    }
                }
                if(flag == 0){
                    Dish dish = new Dish();
                    dish.setName(i.getKey());
                    DishItem item = new DishItem(DishItem.customerOrder, dish ,i.getValue());
                    orderItems.add(item);
                    addItem(item);
                }
            }
        }
        repaint();
    }
}
