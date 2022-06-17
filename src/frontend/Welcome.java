package frontend;

import frontend.Tool.MyEvent;
import frontend.Tool.MyView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Welcome extends MyView {


    public Welcome(){
        JLabel text = new JLabel("欢迎来到自助购餐系统");
        JButton toLogin = new JButton("登录");
        JButton toRegister = new JButton("注册");

//
//
//        add(new Order());
//        add(text);
        add(toLogin);
        add(toRegister);
        toLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispatchMyEvent(new ToLogin());
            }
        });
        toRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispatchMyEvent(new ToRegister());
            }
        });
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.out.println("mouseClicked");
            }
        });
    }


    public class ToLogin extends MyEvent {}
    public class ToRegister extends MyEvent {}
}
