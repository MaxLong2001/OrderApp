package frontend.Customer.order;

import backend.Owner;
import frontend.Tool.MyItem;

import javax.swing.*;

/**
 * 商家详情区域，可以查看商家详情、商家评论
 */
public class OwnerArea extends JPanel {

    JLabel rating;
    JButton comment;
    MyItem content;

    public OwnerArea(Owner owner) {

        rating = new JLabel("评分：" + owner.getRating());
        comment = new JButton();
        comment.setText("查看评价");

        content = new MyItem();
        content.getNameLabel().setText(owner.getName());
        content.getIntroductionArea().setText(owner.getIntroduction());
        content.addLeft(rating);
        content.addRight(comment);
        content.setWidth(800);

        add(content);
    }
}
