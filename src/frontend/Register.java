package frontend;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Register extends MyPanel {
    public Register() {
        add(new InformationText("请输入用户名"));
        add(new Pwd());
        add(new ConfirmPwd());
    }
    class RegisterClick implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }
    class Pwd extends JPanel {
        public Pwd() {
            add(new Information());
            add(new Input());
        }
        class Input extends JPasswordField {
            public Input() {
                super();
            }
        }
        class Information extends JLabel {
            public Information(){
                super("请输入你的密码");
            }
        }
    }
    class ConfirmPwd extends JPanel {
        public ConfirmPwd() {
            add(new Information());
            add(new Input());
        }
        class Input extends JPasswordField {
            public Input() {
                super();
            }
        }
        class Information extends JLabel {
            public Information(){
                super("确认密码");
            }
        }
    }
    class InformationText extends JPanel {
        public InformationText(String information) {
            add(new Information(information));
            add(new Input());
        }

        class Input extends JTextField {
            public Input() {
                super();
            }
        }
        class Information extends JLabel {
            public Information(String information){
                super(information);
            }
        }
    }
}

