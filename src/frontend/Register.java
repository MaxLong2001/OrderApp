package frontend;

import frontend.Tool.FormItem;
import frontend.Tool.MyColor;
import frontend.Tool.MyView;

import javax.swing.*;
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
        JButton registerBtn = new JButton("注册新用户");

        Box box = Box.createVerticalBox();
        box.add(title);
        box.add(Box.createVerticalStrut(30));
        box.add(userName);
        box.add(pwd);
        box.add(confirmPwd);
        box.add(Box.createVerticalStrut(30));
        box.add(registerBtn);
        add(box);
    }
    class RegisterClick implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {

        }
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
}

