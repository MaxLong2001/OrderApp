package frontend;

import javax.swing.*;
import java.awt.*;

public class Welcome extends JPanel {
    public Welcome(){
        JLabel text = new JLabel("��ӭ������������ϵͳ");
        JButton toLogin = new JButton("��¼");
        JButton toRegister = new JButton("ע��");
        add(text);
        add(toLogin);
        add(toRegister);
    }
}
