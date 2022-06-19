package frontend.Owner;

import backend.Customer;
import backend.Owner;
import frontend.Tool.MyButton;
import frontend.Tool.MyItem;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

            }
        });
    }
}
