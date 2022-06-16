package frontend;

import backend.Dish;
import backend.Owner;
import frontend.Tool.MyColor;
import frontend.Tool.MyItem;
import frontend.Tool.MyView;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * 在顾客登录后并进入某个商家的界面后的界面。
 * 主要包含的内容：
 * 菜品列表
 * 评价界面
 */
public class Order extends MyView {

    List<String> dishTypes;

    /**
     * 商家详情区域：
     * 含有商家名称、商家简介、商家评分、查看商家评论的按钮
     */
    OwnerArea ownerArea;


    JPanel orderArea;
    OrderDishList orderDishList;
    OrderCommit orderCommit;

    JPanel dishArea;
    JTabbedPane tabbedPane;
    DishList dishList;

    public Order(){

        setDishArea();
        setOrderArea();

        Owner owner = new Owner();
        owner.setRating(4.5);
        owner.setName("黑猫厨房");
        owner.setIntroduction("黑猫厨房黑猫厨房黑猫厨房黑猫厨房黑猫厨房黑猫厨房黑猫厨房");
        ownerArea = new OwnerArea(owner);

        Box hBox = Box.createHorizontalBox();
        hBox.add(dishArea);
        hBox.add(orderArea);

        Box vBox = Box.createVerticalBox();
        vBox.add(ownerArea);
        vBox.add(hBox);

        add(vBox);
    }

    private void setOrderArea(){
        orderDishList = new OrderDishList();
        orderCommit = new OrderCommit();
        orderCommit.setBackground(MyColor.color2());
        Box vBox = Box.createVerticalBox();
        vBox.add(orderDishList);
        vBox.add(orderCommit);
        orderArea = new JPanel();
        orderArea.add(vBox);
    }
    private void setDishArea(){
        dishList = new DishList();
        tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
        tabbedPane.addTab("12345", dishList);
        JPanel tab = new JPanel();
        tab.add(new JLabel("12344"));
        tabbedPane.setTabComponentAt(0, tab);
        dishArea = new JPanel();
        dishArea.add(tabbedPane);
    }

    class OrderCommit extends Box{
        private double totalPrice = 12.3;
        public OrderCommit(){
            super(BoxLayout.X_AXIS);

            JLabel total = new JLabel();
            total.setText("合计：￥" + totalPrice);
            JPanel totalArea = new JPanel();
            totalArea.setLayout(new FlowLayout(FlowLayout.LEFT));
            totalArea.add(total);

            JButton commit = new JButton();
            commit.setText("查看订单");
            JPanel commitArea = new JPanel();
            commitArea.setLayout(new FlowLayout(FlowLayout.RIGHT));
            commitArea.add(commit);

            Box hBox = Box.createHorizontalBox();

//            hBox.add(totalArea);
//            hBox.add(commitArea);


//            add(hBox);
            add(totalArea);
            add(commitArea);
        }
    }

    class DishList extends JPanel{
        private int listWidth = 400;
        private int listHeight = 400;

        public DishList(){

            Box vBox = Box.createVerticalBox();
            Dish testDish = new Dish();
            testDish.setName("红烧肉");
            testDish.setIntroduction("一款经典，一款经典，一款经典，一款经典，一款经典，一款经典，一款经典，一款经典，一款经典，一款经典，一款经典,一款经典，一款经典，一款经典，一款经典，一款经典，一款经典");
            testDish.setRemainQuantity(20);
            testDish.setPrice(1.2);
            vBox.add(new DishItem(testDish));
            vBox.add(new DishItem(testDish));
            vBox.add(new DishItem(testDish));
            vBox.add(new DishItem(testDish));
            vBox.add(new DishItem(testDish));

            JScrollPane scrollPane = new JScrollPane();
            scrollPane.setPreferredSize(new Dimension(listWidth + 40, listHeight));
            scrollPane.setViewportView(vBox);
            add(scrollPane);
        }
        /**
         * 菜品列表项。包括的内容有：菜品的名称、菜品的剩余数量、一个加号按钮。
         */
        class DishItem extends MyItem {
            int itemWidth = 400;

            public DishItem(Dish dish){
                getNameLabel().setText(dish.getName());
                getIntroductionArea().setText(dish.getIntroduction());

                JLabel number = new JLabel();
                number.setText("剩余数量：" + dish.getRemainQuantity());
                JLabel ordered = new JLabel();
                ordered.setText("" + dish.getRemainQuantity());
                JLabel price = new JLabel();
                price.setText("￥" + dish.getPrice());
                JButton plus = new JButton();
                plus.setText("+");
                JButton minus = new JButton();
                minus.setText("-");

                addLeft(number);
                addLeft(price);
                addRight(minus);
                addRight(ordered);
                addRight(plus);
            }

        }
    }

    class OrderDishList extends JPanel{
        private int listWidth = 400;
        private int listHeight = 400;

        public OrderDishList(){
            Box vBox = Box.createVerticalBox();
            Dish testDish = new Dish();
            testDish.setName("红烧肉");
            testDish.setIntroduction("一款经典，一款经典，一款经典，一款经典，一款经典，一款经典,一款经典，一款经典，一款经典，一款经典，一款经典，一款经典");
            testDish.setRemainQuantity(20);
            testDish.setPrice(1.2);
            vBox.add(new OrderItem(testDish));
            vBox.add(new OrderItem(testDish));
            vBox.add(new OrderItem(testDish));
            vBox.add(new OrderItem(testDish));
            vBox.add(new OrderItem(testDish));
            vBox.add(new OrderItem(testDish));
            vBox.add(new OrderItem(testDish));
            vBox.add(new OrderItem(testDish));

            JScrollPane scrollPane = new JScrollPane();
            scrollPane.setPreferredSize(new Dimension(listWidth + 40, listHeight));
            scrollPane.setViewportView(vBox);
            add(scrollPane);
        }
        class OrderItem extends MyItem{
            public OrderItem(Dish dish){
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



    class OwnerArea extends MyItem {
        JLabel rating;
        JButton comment;

        public OwnerArea(Owner owner) {
            getNameLabel().setText(owner.getName());
            getIntroductionArea().setText(owner.getIntroduction());
            rating = new JLabel("评分：" + owner.getRating());
            addLeft(rating);
            comment = new JButton();
            comment.setText("查看评价");
            addRight(comment);
            setWidth(800);
        }
    }

}
