package frontend.Customer.order;

import backend.*;
import frontend.*;
import frontend.Tool.DishArea;
import frontend.Tool.MyView;

import javax.swing.*;
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
        // todo 获得dishMap
    }

    /**
     * 商家详情区域：
     * 含有商家名称、商家简介、商家评分、查看商家评论的按钮
     */
    OwnerArea ownerArea;
    JPanel orderArea;
    JPanel dishArea;

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
            owner.comments = new ArrayList<>();
            Comment comment = new Comment();
            comment.setCommentTime(new Date());
            comment.setContent("真是太好吃了吧真是太好吃了吧真是太好吃了吧真是太好吃了吧真是太好吃了吧真是太好吃了吧真是太好吃了吧真是太好吃了吧真是太好吃了吧");
            comment.setCustomerName("赵正阳");
            comment.setRating(4.5);
            owner.comments.add(comment);
            owner.comments.add(comment);
            owner.comments.add(comment);
            owner.comments.add(comment);
            owner.comments.add(comment);
            owner.comments.add(comment);
            owner.comments.add(comment);
            owner.comments.add(comment);
            this.currentOwner = owner;
            openWindow(new CommentSubView(owner, loginCustomer), "商家评论");
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


}
