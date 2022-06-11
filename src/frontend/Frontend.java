package frontend;

import javax.swing.*;
import java.awt.*;

public class Frontend extends JFrame{
    /**
     * 使用该构造方法直接构造一个购餐系统的图形化界面
     */
    public Frontend(){
        setSize(1024, 768);
        setTitle("购餐系统");
        
        Container content = getContentPane();
        content.add(new Welcome());
//        JButton btn = new JButton("点击执行一个命令");
        setVisible(true);
    }



}
