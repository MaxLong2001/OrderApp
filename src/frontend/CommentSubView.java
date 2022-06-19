package frontend;

import backend.AppException.AppException;
import backend.Comment;
import backend.Customer;
import backend.Owner;
import frontend.Tool.MyButton;
import frontend.Tool.MyItem;
import frontend.Tool.MyList;
import frontend.Tool.MyView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;


public class CommentSubView extends MyView {

    List<Comment> comments = new ArrayList<>();

    MyList commentItemList;
    NewCommentArea newCommentArea;

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
    public CommentSubView(Owner owner, Customer customer){
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        commentItemList = new MyList();
        commentItemList.setHeight(500);

        comments = owner.comments;
        for (Comment comment : comments){
            CommentItem commentItem = new CommentItem(comment);
            commentItemList.addItem(commentItem);
        }

        newCommentArea = new NewCommentArea(owner, customer);
        add(newCommentArea);
        add(commentItemList);
    }


    class NewCommentArea extends MyItem{
        private Owner owner;
        private Customer customer;

        public NewCommentArea(Owner owner, Customer customer){
            this.owner = owner;
            this.customer = customer;

            getNameLabel().setText("新增评论");
            getIntroductionArea().setEditable();

            //打分区域
            JTextField rating = new JTextField();
            rating.setPreferredSize(new Dimension(60, 30));
            MyButton commitBtn = new MyButton("发送评论");
            commitBtn.setPreferredSize(new Dimension(60, 30));

            addLeft(rating);
            addRight(commitBtn);

            commitBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try{
                        Double ratingNum;
                        try{
                            ratingNum = Double.parseDouble(rating.getText());
                        }catch (Exception exx){
                            throw new AppException("评分格式错误");
                        }
                        Frontend.getLoginCustomer().Comment(ratingNum, getIntroductionArea().getText());
                        Comment comment = new Comment();
                        comment.setRating(ratingNum);
                        comment.setCustomerName(Frontend.getLoginCustomer().getName());
                        comment.setContent(getIntroductionArea().getText());
                        commentItemList.addItem(new CommentItem(comment));
                        repaint();
                        commentItemList.repaint();
                    }catch (AppException ex){
                        ex.message(NewCommentArea.this);
                    }

                }
            });
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
