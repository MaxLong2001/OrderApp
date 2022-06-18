package frontend.Tool;

import javax.swing.*;
import java.awt.*;

public class MyTextArea extends JPanel {
    private JTextArea textArea;
    public MyTextArea(){
        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setCursor(null);
        textArea.setOpaque(false);
        textArea.setFocusable(false);
        textArea.setFont(UIManager.getFont("Label.font"));
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);
        setLayout(new FlowLayout(FlowLayout.LEFT));
        add(textArea);
    }

    public void setText(String text){
        textArea.setText(text);
    }

    /**
     * 实际上设置的是内部显示区域的最佳尺寸，并且height以行数为单位
     */
    public void setPreferredSize(int width, int rows) {
        textArea.setPreferredSize(new Dimension(width, textArea.getPreferredSize().height * rows));
    }

    public void setEditable(){
        textArea.setEditable(true);
        textArea.setFocusable(true);
        textArea.setLineWrap(true);
    }
}
