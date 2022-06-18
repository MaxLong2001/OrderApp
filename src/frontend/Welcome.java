package frontend;

import frontend.Tool.MyButton;
import frontend.Tool.MyColor;
import frontend.Tool.MyEvent;
import frontend.Tool.MyView;

import javax.management.MBeanAttributeInfo;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Welcome extends MyView {


    public Welcome(){


        JLabel text = new JLabel("欢迎来到自助购餐系统");
        JPanel titleArea = new JPanel();
        titleArea.add(text);

        MyButton toLogin = new MyButton("登录");
        toLogin.setPreferredSize(new Dimension(120, 40));
        toLogin.setBackground(MyColor.color6());

        MyButton toRegister = new MyButton("注册");
        toRegister.setPreferredSize(new Dimension(120, 40));
        toRegister.setBackground(MyColor.color6());

        JPanel toLoginArea = new JPanel();
        toLoginArea.add(toLogin);
        JPanel toRegisterArea = new JPanel();
        toRegisterArea.add(toRegister);


        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(titleArea);
        add(toLoginArea);
        add(Box.createVerticalStrut(30));
        add(toRegisterArea);

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
