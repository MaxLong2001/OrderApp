package frontend.Owner;

import backend.Dish;
import backend.Order;
import backend.Owner;
import frontend.CommentSubView;
import frontend.Frontend;
import frontend.ModifyView;
import frontend.Tool.MyButton;
import frontend.Tool.MyItem;
import frontend.Tool.MyView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.NotLinkException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 登录商家后进入的界面
 * 包括的内容有：
 * 未制作订单列表，可以查看订单内容、点击制作订单。
 * 我的菜品列表：查看已有菜品，添加、删除、修改菜品
 */
public class OwnerView extends MyView {
    private Owner loginOwner;

    private OwnerArea ownerArea;
    private ContentArea contentArea;

    public OwnerView(Owner owner) {
        loginOwner = owner;

        ownerArea = new OwnerArea(owner);
        contentArea = new ContentArea(owner);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(ownerArea);
        add(contentArea);
    }

    public class OwnerArea extends MyItem {

        public OwnerArea(Owner owner) {
            getNameLabel().setText(owner.getName());
            getIntroductionArea().setText(owner.getIntroduction());


            MyButton comment = new MyButton("查看评论");
            comment.setPreferredSize(new Dimension(120, 30));
            MyButton modify = new MyButton("修改信息");
            modify.setPreferredSize(new Dimension(120, 30));

            addRight(comment);
            addRight(modify);


            modify.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    openWindow(new ModifyView(loginOwner), "修改信息");
                }
            });

            comment.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    openWindow(new CommentSubView(loginOwner), "商家评论");
                }
            });
        }
    }
}
