package frontend;

import javax.swing.*;
import java.awt.*;

public class Frontend extends JFrame{
    /**
     * ʹ�øù��췽��ֱ�ӹ���һ������ϵͳ��ͼ�λ�����
     */
    public Frontend(){
        setSize(1024, 768);
        setTitle("����ϵͳ");
        
        Container content = getContentPane();
        content.add(new Welcome());
//        JButton btn = new JButton("���ִ��һ������");
        setVisible(true);
    }



}
