package frontend.Customer.order;

import backend.Customer;
import backend.Dish;
import backend.Order;
import backend.Owner;
import frontend.*;
import frontend.Tool.MyColor;
import frontend.Tool.MyItem;
import frontend.Tool.MyView;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * 在顾客登录后并进入某个商家的界面后的界面。
 * 主要包含的内容：
 * 菜品列表
 * 评价界面
 */
public class OrderView extends MyView {

    private Customer loginCustomer;
    private Owner currentOwner;

    private Order currentOrder;

    Map<String, List<Dish>> dishMap;


    private void getData(){
        loginCustomer.SetOwner(currentOwner);
        currentOrder = loginCustomer.CurrentUnfinished();
        if(currentOrder != null) {
            JOptionPane.showConfirmDialog(this, "您有订单尚未完成，请继续完成", "提示", JOptionPane.DEFAULT_OPTION);
        }
        //todo 获得dishMap
    }



    /**
     * 商家详情区域：
     * 含有商家名称、商家简介、商家评分、查看商家评论的按钮
     */
    OwnerArea ownerArea;

    JPanel orderArea;
    OrderDishList orderDishList;
    OrderCommit orderCommit;
    List<DishItem> orderItems;

    JPanel dishArea;
//    JTabbedPane tabbedPane;
//    List<DishList> dishLists;
//    List<DishItem> dishItems;

    public OrderView(Customer loginCustomer, Owner currentOwner){
        if(Frontend.deBug){
            List<Dish> dishes = new ArrayList<>();
            dishes.add(new TestDish());
            dishes.add(new TestDish());
            dishes.add(new TestDish());
            dishes.add(new TestDish());
            dishes.add(new TestDish());
            dishes.add(new TestDish());
            dishMap = new TreeMap<>();
            dishMap.put("家常菜", dishes);
            currentOrder = new TestOrder();
            Owner owner = new Owner();
            owner.setRating(4.5);
            owner.setName("黑猫厨房");
            owner.setIntroduction("黑猫厨房黑猫厨房黑猫厨房黑猫厨房黑猫厨房黑猫厨房黑猫厨房");
            this.currentOwner = owner;
        }else{
            // 初始化数据
            this.loginCustomer = loginCustomer;
            this.currentOwner = currentOwner;
            getData();
        }

        // 绘制三大区域
        ownerArea = new OwnerArea(this.currentOwner);
        dishArea = new DishArea(dishMap, currentOrder);
        orderArea = new OrderArea(currentOrder);

        //排版组合
        Box hBox = Box.createHorizontalBox();
        hBox.add(dishArea);
        hBox.add(orderArea);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(ownerArea);
        add(hBox);
    }


    private void setOrderArea(){
        orderDishList = new OrderDishList(currentOrder);
        orderCommit = new OrderCommit();
        orderCommit.setBackground(MyColor.color2());
        Box vBox = Box.createVerticalBox();
        vBox.add(orderDishList);
        vBox.add(orderCommit);
        orderArea = new JPanel();
        orderArea.add(vBox);
    }



    class OrderCommit extends Box{
        private double totalPrice = currentOrder.getPrice();
        public OrderCommit(){
            super(BoxLayout.X_AXIS);

            JLabel total = new JLabel();
            total.setText("合计：￥" + totalPrice);
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

    class OrderDishList extends JPanel{
        private int listWidth = 400;
        private int listHeight = 400;

        public OrderDishList(Order order){
            Box vBox = Box.createVerticalBox();
            if(Frontend.deBug){
                Dish testDish = new Dish();
                testDish.setName("红烧肉");
                testDish.setIntroduction("一款经典，一款经典，一款经典，一款经典，一款经典，一款经典,一款经典，一款经典，一款经典，一款经典，一款经典，一款经典");
                testDish.setRemainQuantity(20);
                testDish.setPrice(1.2);
                vBox.add(new DishItem(testDish));
                vBox.add(new DishItem(testDish));
                vBox.add(new DishItem(testDish));
                vBox.add(new DishItem(testDish));
                vBox.add(new DishItem(testDish));
                vBox.add(new DishItem(testDish));
                vBox.add(new DishItem(testDish));
                vBox.add(new DishItem(testDish));
            }else{
//                order.dishes
            }


            JScrollPane scrollPane = new JScrollPane();
            scrollPane.setPreferredSize(new Dimension(listWidth + 40, listHeight));
            scrollPane.setViewportView(vBox);
            add(scrollPane);
        }
        class DishItem extends MyItem{
            public DishItem(Dish dish){
                closeIntroduction();
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

                addLeft(price);
                addRight(minus);
                addRight(ordered);
                addRight(plus);
            }
        }
    }




}
