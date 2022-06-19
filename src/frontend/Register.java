package frontend;

import backend.AppException.AppException;
import backend.Customer;
import backend.Owner;
import backend.User;
import frontend.Tool.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Register extends MyView {
    public Register() {
        JLabel title = new JLabel("注册新用户");
        title.setHorizontalAlignment(SwingConstants.CENTER);

        UserName userName = new UserName();
        Pwd pwd = new Pwd();
        ConfirmPwd confirmPwd = new ConfirmPwd();

        JPanel btnPanel = new JPanel();
        MyButton registerBtn = new MyButton("注册新用户");
        btnPanel.add(registerBtn);

        Type type = new Type();

        Box box = Box.createVerticalBox();
        box.add(title);
        box.add(Box.createVerticalStrut(30));
        box.add(userName);
        box.add(pwd);
        box.add(confirmPwd);
        box.add(Box.createVerticalStrut(30));
        box.add(type);
        box.add(Box.createVerticalStrut(30));
        box.add(btnPanel);

        add(box);
        setPreferredSize(new Dimension(500, 500));


        registerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    if(!pwd.getText().equals(confirmPwd.getText())){
                        throw new AppException("两次密码输入不一致");
                    }
                    int selectedType = type.getType();
                    User newUser ;
                    if(selectedType == 1){
                        newUser = new Customer();

                    }else if (selectedType == 2){
                        newUser = new Owner();
                    }else {
                        throw new AppException("请选择注册类型");
                    }
                    newUser.setName(userName.getText());
                    newUser.setPassword(pwd.getText());
                    User.register(newUser);
                    JOptionPane.showConfirmDialog(Register.this,
                            "注册成功", "注册成功", JOptionPane.DEFAULT_OPTION);
                    if(selectedType == 1){
                        dispatchMyEvent(new DoneRegisterEvent(new Customer(userName.getText(), pwd.getText())));
                    }else{
                        dispatchMyEvent(new DoneRegisterEvent(new Owner(userName.getText(), pwd.getText())));
                    }
                }catch (AppException ex){
                    JOptionPane.showConfirmDialog(Register.this,
                            ex, "注册异常", JOptionPane.DEFAULT_OPTION);
                }


            }
        });
    }
    class Pwd extends FormItem {
        public Pwd() {
            super("请输入密码:", true);
        }
    }
    class ConfirmPwd extends FormItem {
        public ConfirmPwd() {
            super("请确认密码:", true);
        }
    }
    class UserName extends FormItem {
        public UserName(){
            super("请输入用户名:");
        }
    }
    class Type extends JPanel {
        JRadioButton customerSelect;
        JRadioButton ownerSelect;

        public Type(){
            Box box = Box.createHorizontalBox();
            JLabel label = new JLabel();
            label.setPreferredSize(new Dimension(150, getPreferredSize().height));
            label.setHorizontalAlignment(SwingConstants.RIGHT);
            label.setText("选择注册类型");
            label.setFont(MyFont.font1());

            ButtonGroup group = new ButtonGroup();

            customerSelect = new JRadioButton("顾客");
            ownerSelect = new JRadioButton("商家");
            group.add(customerSelect);
            group.add(ownerSelect);

            box.add(label);
            box.add(Box.createHorizontalStrut(20));
            box.add(customerSelect);
            box.add(ownerSelect);
//        setBackground(MyColor.color1());
            add(box);
        }

        /**
         *
         * @return 0表示没有选择，1表示顾客，2表示商家
         */
        public int getType(){
            if(customerSelect.isSelected())return 1;
            else if(ownerSelect.isSelected()) return 2;
            else return 0;
        }
    }

    public class DoneRegisterEvent extends MyEvent {
        private User loginUser;

        public User getLoginUser() {
            return loginUser;
        }

        public DoneRegisterEvent(User user) {
            this.loginUser = user;
        }
    }
}

