package frontend;

import backend.Comment;
import backend.Customer;
import backend.Owner;
import frontend.Tool.MyItem;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class CommentSubView extends JPanel {

    List<Comment> comments = new ArrayList<>();

    public CommentSubView(Owner owner){

        comments = owner.comments;
        for (Comment comment : comments){

        }
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