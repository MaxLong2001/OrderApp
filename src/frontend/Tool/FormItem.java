package frontend.Tool;

import javax.swing.*;
import javax.swing.border.AbstractBorder;
import javax.swing.border.EmptyBorder;
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
        label.setFont(MyFont.font1());

        if(isPwd){
            textField = new JPasswordField(textCol);
        }else{
            textField = new JTextField(textCol);
        }
//            textField.setPreferredSize(new Dimension(100, getFont().getSize()*2));
//        textField.setBorder(BorderFactory.createCompoundBorder(
//                new CustomeBorder(),
//                new EmptyBorder(new Insets(15, 25, 15, 25))));
        textField.setBorder(new EmptyBorder(new Insets(10, 20, 10, 20)));
//        textField.setBorder(new CustomeBorder());

        box.add(label);
        box.add(Box.createHorizontalStrut(interval));
        box.add(textField);
//        setBackground(MyColor.color1());
        add(box);

    }
    static class CustomeBorder extends AbstractBorder {
        @Override
        public void paintBorder(Component c, Graphics g, int x, int y,
                                int width, int height) {
            // TODO Auto-generated method stubs
            super.paintBorder(c, g, x, y, width, height);
            Graphics2D g2d = (Graphics2D)g;
            g2d.setStroke(new BasicStroke(10));
            g2d.setColor(MyColor.color1());
            g2d.drawRoundRect(x, y, width - 1, height - 1, 25, 25);
        }
    }
}
