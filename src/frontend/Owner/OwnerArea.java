package frontend.Owner;

import backend.Customer;
import backend.Owner;
import frontend.CommentSubView;
import frontend.Frontend;
import frontend.ModifyView;
import frontend.Tool.MyButton;
import frontend.Tool.MyItem;
import frontend.Tool.MyView;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OwnerArea extends MyItem {
    MyButton comment;
    MyButton modify;
    MyButton refresh;
    Owner owner;
    public OwnerArea() {
        owner = Frontend.getLoginOwner();
        getNameLabel().setText(owner.getName());
        getIntroductionArea().setText(owner.getIntroduction());


        comment = new MyButton("查看评论");
        comment.setPreferredSize(new Dimension(120, 30));
        modify = new MyButton("修改信息");
        modify.setPreferredSize(new Dimension(120, 30));
        refresh = new MyButton("刷新界面");
        refresh.setPreferredSize(new Dimension(120, 30));

        addRight(refresh);
        addRight(comment);
        addRight(modify);

        modify.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MyView.openWindow(new ModifyView(owner), "修改信息");
            }
        });
        comment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MyView.openWindow(new CommentSubView(owner), "商家评论");
            }
        });
    }

    public MyButton getComment() {
        return comment;
    }

    public MyButton getModify() {
        return modify;
    }

    public MyButton getRefresh() {
        return refresh;
    }

    public void refresh() {
        getNameLabel().setText(owner.getName());
        getIntroductionArea().setText(owner.getIntroduction());
        repaint();
    }
}
