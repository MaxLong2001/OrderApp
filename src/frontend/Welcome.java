package frontend;

import javax.swing.*;
import java.awt.*;

public class Welcome extends JPanel {
    public Welcome(){
        JLabel text = new JLabel("欢迎来到自助购餐系统");
        JButton toLogin = new JButton("登录");
        JButton toRegister = new JButton("注册");
        add(text);
        add(toLogin);
        add(toRegister);
    }
}
