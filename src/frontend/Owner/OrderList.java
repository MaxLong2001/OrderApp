package frontend.Owner;

import backend.Order;
import frontend.Frontend;
import frontend.Tool.MyButton;
import frontend.Tool.MyItem;
import frontend.Tool.MyList;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderList extends MyList {
    List<Order> orders;

    public OrderList(List<Order> orders) {
        this.orders = orders;

        for(Order o : orders){
            OrderItem orderItem = new OrderItem(o);
            addItem(orderItem);
        }
        setHeight(400);
    }
//    private void refresh(){
//        List<Order> orders;
//        if(Frontend.deBug){
//            orders = new ArrayList<>();
//            Order order = new Order();
//            order.nameOfCustomer = "赵正阳";
//            order.orderTime = new Date();
//            order.cooked = false;
//            orders.add(order);
//            orders.add(order);
//            orders.add(order);
//        }else {
//            orders = loginOwner.ShowUncooked();
//        }
//
//    }
}
class OrderItem extends MyItem {
    public OrderItem(Order order) {
        getNameLabel().setText(order.nameOfCustomer + "的订单");

        JLabel time = new JLabel();
        time.setText("创建时间：" + order.getOrderTime());

        JLabel state = new JLabel();
        state.setText(order.cooked ? "已制作" : "尚未制作");
        MyButton btn = new MyButton("查看内容");

        addLeft(time);
        addLeft(state);
        addRight(btn);
        closeIntroduction();
    }
}