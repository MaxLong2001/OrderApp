package frontend;

import backend.AppException.AppException;
import backend.Customer;
import backend.User;
import frontend.Tool.FormItem;
import frontend.Tool.MyButton;
import frontend.Tool.MyEvent;
import frontend.Tool.MyView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * 当点击登录后该类如果获得登录的对象则传给
 */
public class Login extends MyView {


    private String userName;
    private String pwd;

    private NameInput nameInput;
    private PwdInput pwdInput;

    private User loginUser;

    public Login(){
        nameInput = new NameInput();
        pwdInput = new PwdInput();
        LoginBtn loginBtn = new LoginBtn();
        JPanel btnPanel = new JPanel();
        btnPanel.add(loginBtn);


        Box vBox = Box.createVerticalBox();
        vBox.add(nameInput);
        vBox.add(pwdInput);
        vBox.add(btnPanel);

        add(vBox);
        setPreferredSize(new Dimension(500, 500));
    }
    class NameInput extends FormItem {
        public NameInput(){
            super("请输入用户名");
        }
    }
    class PwdInput extends FormItem {
        public PwdInput(){
            super("请输入密码", true);
        }
    }
    class LoginBtn extends MyButton {
        public LoginBtn(){
            super("登录");

            addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    userName = nameInput.getText();
                    pwd = pwdInput.getText();
                    if(Frontend.deBug){
                        dispatchMyEvent(new DoneLoginEvent(loginUser));
                    }else {
                        try{
                        loginUser = User.login(userName, pwd);
                            dispatchMyEvent(new DoneLoginEvent(loginUser));
                        }catch (AppException ex){
                            JOptionPane.showConfirmDialog(Login.this, ex, "登录异常", JOptionPane.DEFAULT_OPTION);
                        }
                    }
                }
            });
        }
    }

    public class DoneLoginEvent extends MyEvent {
        private User loginUser;

        public User getLoginUser() {
            return loginUser;
        }

        public DoneLoginEvent(User user) {
            this.loginUser = user;
        }
    }
}

