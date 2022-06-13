package frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Welcome extends JPanel {

    public Welcome(){
        JLabel text = new JLabel("欢迎来到自助购餐系统");
        JButton toLogin = new JButton("登录");
        JButton toRegister = new JButton("注册");
        add(text);
        add(toLogin);
        add(toRegister);
        toLogin.addActionListener(new btnActionListener());
    }
    class btnActionListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            System.out.println("你点击了一个按钮用来测试！");

        }
    }
}
