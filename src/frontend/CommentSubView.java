package frontend;

import backend.Comment;
import backend.Customer;
import backend.Owner;
import frontend.Tool.MyItem;
import frontend.Tool.MyList;
import frontend.Tool.MyView;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class CommentSubView extends MyView {

    List<Comment> comments = new ArrayList<>();

    MyList commentItemList;

    public CommentSubView(Owner owner){
        commentItemList = new MyList();
        commentItemList.setHeight(500);

        comments = owner.comments;
        for (Comment comment : comments){
            CommentItem commentItem = new CommentItem(comment);
            commentItemList.addItem(commentItem);
        }

        add(commentItemList);
    }
}
class CommentItem extends MyItem{
    public CommentItem(Comment comment){
        getNameLabel().setText(comment.getCustomerName() + "的评论");
        getIntroductionArea().setText(comment.getContent());
        JLabel rating = new JLabel();
        rating.setText(String.format("%.2f", comment.getRating()) + "分");

        addRight(rating);
    }
}