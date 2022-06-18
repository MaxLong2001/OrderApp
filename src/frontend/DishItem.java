package frontend;

import backend.Dish;
import backend.Order;
import frontend.Tool.MyButton;
import frontend.Tool.MyItem;

import javax.swing.*;

public class DishItem extends MyItem {
    public static final int customerBrowse = 0;
    public static final int ownerBrowse = 1;
    public static final int customerOrder = 2;
    public static final int ownerOrder = 3;

//    public DishItem(int type, String dishName, int orderdNum){
//        Dish dish = new Dish();
//        dish.setName(dishName);
//        this(type, dish, orderdNum);
//    }
    public DishItem(int type, Dish dish, int orderdNum) {


        getNameLabel().setText(dish.getName());

        if(type == customerBrowse || type == ownerBrowse){
            getIntroductionArea().setText(dish.getIntroduction());
        }else{
            closeIntroduction();
        }


        JLabel remain = new JLabel();
        remain.setText("剩余数量 : " + dish.getRemainQuantity());

        JLabel price = new JLabel();
        price.setText("￥" + dish.getPrice());

        JLabel amount = new JLabel();
        amount.setText("￥" + dish.getPrice() * orderdNum);

        MyButton plus = new MyButton("+");
        MyButton minus = new MyButton("-");

        JLabel ordered = new JLabel();
        ordered.setText("" + orderdNum);
        JLabel ordered2 = new JLabel();
        ordered.setText("数量 : " + orderdNum);



        if(type == customerOrder){
//            addLeft(amount);

            addRight(minus);
            addRight(ordered);
            addRight(plus);
        }else if(type == ownerOrder){
//            addLeft(amount);

            addRight(ordered2);
        }else if(type == customerBrowse){
            addLeft(remain);
            addLeft(price);
            if(orderdNum > 0){
                addRight(minus);
                addRight(ordered);
            }
            addRight(plus);
        }else if(type == ownerBrowse){
            MyButton modify = new MyButton("修改菜品");

            addLeft(remain);
            addLeft(price);

            addRight(modify);
        }
    }

}
