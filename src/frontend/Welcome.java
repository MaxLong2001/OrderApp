package frontend;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Welcome extends MyView {


    public Welcome(){
        JLabel text = new JLabel("欢迎来到自助购餐系统");
        JButton toLogin = new JButton("登录");
        JButton toRegister = new JButton("注册");
//        add(new Order());
//        add(text);
//        add(toLogin);
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
    }
    public class ToLogin extends MyEvent {}
    public class ToRegister extends MyEvent {}
}
