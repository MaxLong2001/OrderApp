package frontend.Tool;

import frontend.Tool.MyColor;
import frontend.Tool.MyTextArea;

import javax.swing.*;
import java.awt.*;

/**
 * 一个从上到下分别为名称、简介、操作的Item
 */
public class MyItem extends JPanel {
    int itemWidth = 400;

    private JPanel nameArea;
    private JLabel name;
    private MyTextArea introductionArea;
    private Box bottomArea;
    private JPanel left;
    private JPanel right;

    public JLabel getNameLabel(){
        return name;
    }
    public MyTextArea getIntroductionArea(){
        return introductionArea;
    }
    public void closeIntroduction(){
        remove(introductionArea);
    }
    public void addLeft(JComponent a){
        left.add(a);
    }
    public void addRight(JComponent a){
        right.add(a);
    }
    public void setWidth(int width){
        itemWidth = width;
        introductionArea.setPreferredSize(itemWidth, 2);
    }

    public MyItem(){
//        super(BoxLayout.Y_AXIS);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        nameArea = new JPanel();
        nameArea.setLayout(new FlowLayout(FlowLayout.LEFT));
        name = new JLabel();
        nameArea.add(name);
        nameArea.setBackground(MyColor.color2());

        introductionArea = new MyTextArea();
        introductionArea.setPreferredSize(itemWidth, 2);

        left = new JPanel();
        right = new JPanel();
        left.setLayout(new FlowLayout(FlowLayout.LEFT));
        right.setLayout(new FlowLayout(FlowLayout.RIGHT));

        bottomArea = Box.createHorizontalBox();
        bottomArea.add(left);
        bottomArea.add(right);

//        Box vBox = Box.createVerticalBox();
//        vBox.add(Box.createHorizontalStrut(itemWidth));
//        vBox.add(nameArea);
//        if(!isEasy) vBox.add(introductionArea);
//        vBox.add(hBox);
//
//        add(vBox);
        setBackground(MyColor.color1());
        add(nameArea);
        add(introductionArea);
        add(bottomArea);
    }
}
