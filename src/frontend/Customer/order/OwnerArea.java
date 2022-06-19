package frontend.Customer.order;

import backend.Owner;
import frontend.CommentSubView;
import frontend.Frontend;
import frontend.Tool.MyButton;
import frontend.Tool.MyItem;
import frontend.Tool.MyView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 商家详情区域，可以查看商家详情、商家评论
 */
public class OwnerArea extends JPanel {

    JLabel rating;
    JButton comment;
    MyItem content;

    public OwnerArea(Owner owner) {

        rating = new JLabel("评分：" + owner.getRating());
        comment = new MyButton("查看评价");
        comment.setPreferredSize(new Dimension(60, 30));


        content = new MyItem();
        content.getNameLabel().setText(owner.getName());
        content.getIntroductionArea().setText(owner.getIntroduction());
        content.addLeft(rating);
        content.addRight(comment);
        content.setWidth(800);

        add(content);

        comment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MyView.openWindow(new CommentSubView(Frontend.currentOwner, Frontend.getLoginCustomer()),
                        "商家评论");
            }
        });
    }
}
