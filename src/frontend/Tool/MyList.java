package frontend.Tool;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 一个带有滚动条的列表
 */
public class MyList extends JPanel{
    JScrollPane scrollPane;
//    private int width = 400;
    private int height = 400;

    List<JPanel> orderItems = new ArrayList<>();
    Box vBox;
    public MyList(){
        vBox = Box.createVerticalBox();
        scrollPane = new JScrollPane();
        scrollPane.setViewportView(vBox);
        add(scrollPane);
    }

    public void addItem(JPanel item){
        vBox.add(item);
        scrollPane.setPreferredSize(new Dimension(item.getPreferredSize().width + 40, height));
        repaint();
    }
    public void setHeight(int height){
        this.height = height;
        scrollPane.setPreferredSize(new Dimension(scrollPane.getPreferredSize().width, height));
        repaint();
    }
}
