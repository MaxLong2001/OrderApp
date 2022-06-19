package frontend.Customer.order;

import backend.*;
import backend.AppException.AppException;
import frontend.*;
import frontend.Tool.DishArea;
import frontend.Tool.DishList;
import frontend.Tool.MyView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

    Map<String, List<Dish>> dishMap;


    private void getData(){
        loginCustomer.SetOwner(currentOwner);
        Frontend.currentOrder = loginCustomer.CurrentUnfinished();
        if(Frontend.currentOrder != null) {
            JOptionPane.showConfirmDialog(this, "您有订单尚未完成，请继续完成", "提示", JOptionPane.DEFAULT_OPTION);
        }
        try{
            dishMap = currentOwner.showDishes(currentOwner.getName());
        }catch (AppException e){
            e.message(this);
        }
    }

    /**
     * 商家详情区域：
     * 含有商家名称、商家简介、商家评分、查看商家评论的按钮
     */
    OwnerArea ownerArea;
    OrderArea orderArea;
    DishArea dishArea;

    public OrderView(Owner currentOwner){
        // 初始化数据
        this.loginCustomer = Frontend.getLoginCustomer();
        loginCustomer.SetOwner(currentOwner);
        this.currentOwner = currentOwner;
        getData();

        // 绘制三大区域
        ownerArea = new OwnerArea(this.currentOwner);
        dishArea = new DishArea(dishMap, Frontend.currentOrder);
        orderArea = new OrderArea();

        //排版组合
        Box hBox = Box.createHorizontalBox();
        hBox.add(dishArea);
        hBox.add(orderArea);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(ownerArea);
        add(hBox);

        Set<Map.Entry<String, DishList>> dishListMap = dishArea.dishListMap.entrySet();
        for(Map.Entry<String, DishList> dishListEntry : dishListMap){
            List<DishItem> dishItems = dishListEntry.getValue().dishItems;
            for(DishItem item : dishItems){
                item.customerPlus.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try{
                            Frontend.getLoginCustomer().SetDish(item.dish);
                            Frontend.getLoginCustomer().AddInOrder();
                            orderArea.refresh();
                            repaint();
                        }catch (AppException ex){
                            JOptionPane.showConfirmDialog(OrderView.this,
                                    ex, "错误提示", JOptionPane.DEFAULT_OPTION);
                        }

                    }
                });
            }
        }


    }


}
