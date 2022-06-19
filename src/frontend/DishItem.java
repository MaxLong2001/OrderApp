package frontend;

import backend.AppException.AppException;
import backend.Customer;
import backend.Dish;
import backend.Order;
import backend.Owner;
import frontend.Tool.MyButton;
import frontend.Tool.MyItem;
import frontend.Tool.MyView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    Customer customer;
    Owner owner;

    MyButton modify;
    MyButton ownerPlus;
    MyButton customerMinus;
    MyButton customerPlus;
    Dish dish;
    public DishItem(int type, Dish dish, int orderdNum) {
        this.customer = Frontend.getLoginCustomer();
        this.owner = Frontend.getLoginOwner();
        this.dish = dish;

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

        customerPlus = new MyButton("+");
        customerMinus = new MyButton("-");
        modify = new MyButton("修改菜品");
        ownerPlus = new MyButton("+");


        if(type == customerOrder){

            addRight(customerMinus);
            addRight(ordered);
            addRight(customerPlus);
        }else if(type == ownerOrder){
//            addLeft(amount);

            addRight(ordered2);
        }else if(type == customerBrowse){
            addLeft(remain);
            addLeft(price);
            if(orderdNum > 0){
                addRight(customerMinus);
                addRight(ordered);
            }
            addRight(customerPlus);
        }else if(type == ownerBrowse){
            addLeft(remain);
            addLeft(price);
            addRight(ownerPlus);
            addRight(modify);
        }

        customerPlus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    customer.SetDish(dish);
                    customer.AddInOrder();
                }catch (AppException ex){
                    JOptionPane.showConfirmDialog(DishItem.this,
                            ex, "错误提示", JOptionPane.DEFAULT_OPTION);
                }
            }
        });
        customerMinus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    customer.SetDish(dish);
                    customer.DelInOrder();
                }catch (AppException ex){
                    ex.message(DishItem.this);
                }

            }
        });

        ownerPlus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    owner.addDishQuantity(owner.getName(), dish.getName());
                }catch (AppException ex){
                    ex.message(DishItem.this);
                }
            }
        });
        modify.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MyView.openWindow(new ModifyDish(owner, dish.name), "修改菜品");
            }
        });
    }


    public MyButton getModify() {
        return modify;
    }

    public Dish getDish() {
        return dish;
    }
}
