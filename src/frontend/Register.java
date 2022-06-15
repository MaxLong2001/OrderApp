package frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Register extends MyView {
    public Register() {
        Box box = Box.createVerticalBox();
//        GridLayout layout = new GridLayout(4, 1);
//        setLayout(layout);
        UserName userName = new UserName();
        JLabel title = new JLabel("注册新用户");
//        title.setBackground(new Color(15, 244, 244));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        box.add(title);
        box.add(Box.createVerticalStrut(30));
        box.add(userName);
        box.add(new Pwd());
        box.add(new ConfirmPwd());
        box.add(Box.createVerticalStrut(30));
        box.add(new JButton("注册新用户"));
        add(box);

    }
    class RegisterClick implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }
    class Item extends JPanel{
        int textCol = 15;
        int leftWidth = 150;
        int interval = 20;

        public Item(String information, boolean isPwd){
            Box box = Box.createHorizontalBox();
            Box.createHorizontalStrut(20);
            FlowLayout layout = new FlowLayout();
//            layout.setHgap(10);
//            setLayout(layout);
//            setPreferredSize(new Dimension(Register.this.getWidth()/2, getFont().getSize()));
//            System.out.println(getHeight());
            JLabel label = new JLabel();
            label.setPreferredSize(new Dimension(leftWidth, getFont().getSize()*2));
            label.setHorizontalAlignment(SwingConstants.TRAILING);
            label.setText(information);
            JTextField textField;
            if(isPwd){
                textField = new JPasswordField(textCol);
            }else{
                textField = new JTextField(textCol);
            }
            textField.setPreferredSize(new Dimension(100, getFont().getSize()*2));
//            textField.setPreferredSize(new Dimension(Register.this.getWidth()/4, textField.getHeight()));
//            textField.setPreferredSize(new Dimension(100, 40));
            box.add(label);
            box.add(Box.createHorizontalStrut(interval));
            box.add(textField);
            setBackground(new Color(15, 244, 244));
            add(box);
        }
    }
    class Pwd extends Item {
        public Pwd() {
            super("请输入密码:", true);
        }
    }
    class ConfirmPwd extends Item {
        public ConfirmPwd() {
            super("请确认密码:", true);
        }
    }
    class UserName extends Item {
        public UserName(){
            super("请输入用户名:", false);
        }
    }
}

