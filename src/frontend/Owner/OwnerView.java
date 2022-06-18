package frontend.Owner;

import backend.Dish;
import backend.Order;
import backend.Owner;
import frontend.Frontend;
import frontend.Tool.MyButton;
import frontend.Tool.MyItem;
import frontend.Tool.MyView;

import javax.swing.*;
import java.awt.*;
import java.nio.file.NotLinkException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * 登录商家后进入的界面
 * 包括的内容有：
 * 未制作订单列表，可以查看订单内容、点击制作订单。
 * 我的菜品列表：查看已有菜品，添加、删除、修改菜品
 */
public class OwnerView extends MyView {
    private Owner loginOwner;

    public OwnerView() {
        OrderList orderList;
        orderList = new OrderList();

        add(orderList);

    }

    class OrderList extends JPanel{
        public OrderList() {
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            refresh();
        }
        private void refresh(){
            List<Order> orders;
            if(Frontend.deBug){
                orders = new ArrayList<>();
                Order order = new Order();
                order.nameOfCustomer = "赵正阳";
                order.orderTime = new Date(2000);
                order.cooked = false;
                orders.add(order);
                orders.add(order);
                orders.add(order);
            }else {
                // todo 改成show Uncooked
                orders = loginOwner.ShowUnFinished();
            }

            for(Order o : orders){
                OrderItem orderItem = new OrderItem(o);
                add(orderItem);
            }
        }
    }
    class OrderItem extends MyItem{
        public OrderItem(Order order) {
            getNameLabel().setText(order.nameOfCustomer + "的订单");

            JLabel time = new JLabel();
            time.setText("创建时间：" + order.getOrderTime());

            JLabel state = new JLabel();
            state.setText(order.cooked ? "已制作" : "尚未制作");
            MyButton btn = new MyButton("查看内容");
//            btn.setPreferredSize(new Dimension());

            addLeft(time);
            addLeft(state);
            addRight(btn);
            closeIntroduction();
        }
    }
    class OrderDetail extends JPanel{
        public OrderDetail(Order order) {
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        }
        class DishItem extends MyItem{
            public DishItem(Dish dish){

                getNameLabel().setText(dish.getName());
                getIntroductionArea().setText(dish.getIntroduction());

                JLabel ordered = new JLabel();
                ordered.setText("" + dish.getRemainQuantity());
                JLabel price = new JLabel();
                price.setText("￥" + dish.getPrice());
                JButton plus = new JButton();
                plus.setText("+");
                JButton minus = new JButton();
                minus.setText("-");

                closeIntroduction();
                addLeft(price);
                addRight(minus);
                addRight(ordered);
                addRight(plus);
            }
        }
    }
}
