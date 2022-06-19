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
import java.awt.*;
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
    public MyButton customerPlus;
    public Dish dish;
    public JLabel ordered;
    public int orderdNum;
    public DishItem(int type, Dish dish, int orderdNum) {
        this.orderdNum = orderdNum;
        this.customer = Frontend.getLoginCustomer();
        if(type == customerBrowse || type == customerOrder){
            this.owner = Frontend.currentOwner;
        }else{
            this.owner = Frontend.getLoginOwner();

        }
        this.dish = dish;

        getNameLabel().setText(dish.getName());

        if(type == customerBrowse || type == ownerBrowse){
            getIntroductionArea().setText(dish.getIntroduction());
        }else{
            closeIntroduction();
        }


        JLabel remain = new JLabel();
        remain.setText("剩余数量 : " + dish.getRemainQuantity());
        remain.setFont(new Font("黑体", Font.PLAIN, 15));

        JLabel price = new JLabel();
        price.setText("￥" + dish.getPrice());
        price.setFont(new Font("黑体", Font.PLAIN, 15));


        JLabel amount = new JLabel();
        amount.setText("￥" + dish.getPrice() * orderdNum);
        amount.setFont(new Font("黑体", Font.PLAIN, 15));



        ordered = new JLabel();
        ordered.setText("" + orderdNum);
        ordered.setFont(new Font("黑体", Font.PLAIN, 15));
        JLabel ordered2 = new JLabel();
        ordered2.setText("数量 : " + orderdNum);
        ordered2.setFont(new Font("黑体", Font.PLAIN, 15));

        customerPlus = new MyButton("+");
        customerPlus.setPreferredSize(new Dimension(30, 30));
        customerMinus = new MyButton("-");
        customerMinus.setPreferredSize(new Dimension(30, 30));
        modify = new MyButton("修改菜品");
        modify.setPreferredSize(new Dimension(50, 30));
        ownerPlus = new MyButton("+");
        ownerPlus.setPreferredSize(new Dimension(30, 30));



        if(type == customerOrder){

//            addRight(customerMinus);
            addRight(ordered);
//            addRight(customerPlus);
        }else if(type == ownerOrder){
//            addLeft(amount);

            addRight(ordered2);
        }else if(type == customerBrowse){
//            addLeft(remain);
            addLeft(price);
//            if(orderdNum > 0){
//                addRight(customerMinus);
//                addRight(ordered);
//            }
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
//                try{
//                    customer.SetDish(dish);
//                    customer.AddInOrder();
//                    todo 刷新order界面
//
//                }catch (AppException ex){
//                    JOptionPane.showConfirmDialog(DishItem.this,
//                            ex, "错误提示", JOptionPane.DEFAULT_OPTION);
//                }
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
                    dish.setRemainQuantity(dish.getRemainQuantity() + 1);
                    remain.setText("剩余数量 : " + dish.getRemainQuantity());
                    remain.repaint();
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

        setPreferredSize(new Dimension(400, 150));
        setMaximumSize(new Dimension(400, 150));
    }


    public MyButton getModify() {
        return modify;
    }

    public Dish getDish() {
        return dish;
    }

    public void addOrderNum(){
        orderdNum ++;
        ordered.setText("" + orderdNum);
        ordered.repaint();
        repaint();
    }

}
