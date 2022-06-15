package frontend;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * 当点击登录后该类如果获得登录的对象则传给
 */
public class Login extends MyView {


    Object loginUser;

    public Login(){
        add(new NameInput());
        add(new PwdInput());
        LoginBtn loginBtn = new LoginBtn();
        loginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispatchMyEvent(new DoneLoginEvent());
            }
        });
        add(loginBtn);
    }
    class NameInput extends JTextField {
        public NameInput(){
            super("请输入用户名");
        }
    }
    class PwdInput extends JTextField {
        public PwdInput(){
            super("请输入密码");
        }
    }
    class LoginBtn extends JButton {
        public LoginBtn(){
            super("登录");
        }
    }

    public class DoneLoginEvent extends MyEvent{
        Object loginUser;
        public DoneLoginEvent() {
            this.loginUser = Login.this.loginUser;
        }
    }
}

