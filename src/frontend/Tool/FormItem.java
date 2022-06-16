package frontend.Tool;

import javax.swing.*;
import java.awt.*;

public class FormItem extends JPanel {
    int textCol = 15;
    int leftWidth = 150;
    int interval = 20;

    JTextField textField;
    public String getText(){
        return textField.getText();
    }

    public FormItem(String information){
        this(information, false);
    }

    public FormItem(String information, boolean isPwd){
        Box box = Box.createHorizontalBox();
        JLabel label = new JLabel();
        label.setPreferredSize(new Dimension(leftWidth, getPreferredSize().height));
        label.setHorizontalAlignment(SwingConstants.RIGHT);
        label.setText(information);

        if(isPwd){
            textField = new JPasswordField(textCol);
        }else{
            textField = new JTextField(textCol);
        }
//            textField.setPreferredSize(new Dimension(100, getFont().getSize()*2));
        box.add(label);
        box.add(Box.createHorizontalStrut(interval));
        box.add(textField);
        setBackground(MyColor.color1());
        add(box);
    }
}
