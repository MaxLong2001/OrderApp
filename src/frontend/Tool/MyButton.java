package frontend.Tool;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MyButton extends JButton {
    static final int Normal = 0;
    static final int Focus = 1;
    static final int Click = 2;
    int state = Normal;
    int width = 200;
    int height = 50;
    JLabel textLabel;
    Color colorNormal = new Color(MyColor.color3().getRed(), MyColor.color3().getGreen(), MyColor.color3().getBlue(), (int) (MyColor.color3().getAlpha() * 0.8));
    Color colorFocus = new Color(colorNormal.getRed(), colorNormal.getGreen(), colorNormal.getBlue(), (int) (colorNormal.getAlpha() * 0.8));
    Color colorPress = new Color(colorNormal.getRed(), colorNormal.getGreen(), colorNormal.getBlue(), (int) (colorNormal.getAlpha() * 1.2));
    public MyButton(String text){
        setBackground(MyColor.color3());
        setLayout(null);
        textLabel = new JLabel();
        textLabel.setFont(new Font("黑体", Font.PLAIN, 20));
        textLabel.setText(text);
        setPreferredSize(new Dimension(200, 50));
        add(textLabel);
        setContentAreaFilled(false);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                state = Focus;
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                state = Normal;
//                System.out.println("exited");
                repaint();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                state = Click;
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if(e.getX() >= 0 && e.getX() <= getPreferredSize().width &&
                        e.getY() >= 0 && e.getY() <= getPreferredSize().height){
                    state = Focus;
//                    System.out.println("state = focus");
                }else {
                    state = Normal;
                }
                repaint();
            }
        });
    }

//    @Override
//    public void setPreferredSize(Dimension preferredSize) {
//        width = preferredSize.width;
//        height = preferredSize.height;
//        textLabel.setFont(new Font(textLabel.getFont().getFontName(), Font.PLAIN, height * 2 / 5));
//        textLabel.setSize(textLabel.getPreferredSize());
//        textLabel.setLocation((width - textLabel.getWidth()) / 2, (height - textLabel.getHeight()) / 2);
//    }

//    @Override
//    public Dimension getPreferredSize() {
//        return new Dimension(width, height);
//    }
//
//    @Override
//    public Dimension getMaximumSize() {
//        return new Dimension(width, height);
//    }
//
//    @Override
//    public Dimension getMinimumSize() {
//        return new Dimension(width, height);
//    }

    @Override
    protected void paintComponent(Graphics g) {
        int width = getPreferredSize().width;
        int height = getPreferredSize().height;
        int r = getPreferredSize().height / 2;
        Color renderColor = new Color(colorNormal.getRed(), colorNormal.getGreen(), colorNormal.getBlue(), (int) (colorNormal.getAlpha() * 0.8));
        switch (state){
//            case Normal -> color();
            case Focus -> renderColor = new Color(renderColor.getRed(), renderColor.getGreen(), renderColor.getBlue(), (int) (renderColor.getAlpha() * 0.8));
            case Click -> renderColor = new Color(renderColor.getRed(), renderColor.getGreen(), renderColor.getBlue(), (int) (renderColor.getAlpha() * 1.2));
        }
//        System.out.println(renderColor);
//        Graphics2D graphics2D = (Graphics2D) g;

//        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
//                RenderingHints.VALUE_ANTIALIAS_ON);

//        graphics2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
//                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g.setColor(renderColor);
        g.drawRoundRect(0, 0, width, height, r, r);
        g.fillRoundRect(0, 0, width, height, r, r);
//        super.paintComponent(g);

    }

    @Override
    public void setPreferredSize(Dimension preferredSize) {
        super.setPreferredSize(preferredSize);
        width = preferredSize.width;
        height = preferredSize.height;
        textLabel.setSize(textLabel.getPreferredSize());
        textLabel.setLocation((width - textLabel.getWidth()) / 2, (height - textLabel.getHeight()) / 2);
    }

    @Override
    protected void paintBorder(Graphics g) {
//        super.paintBorder(g);
    }

    @Override
    public void setBackground(Color bg) {
        super.setBackground(bg);
        colorNormal = bg;
        colorPress = darker(bg);
        colorFocus = brighter(bg);
    }

    double darkFactor = 0.9;
    public Color darker(Color c){
        return new Color((int)(c.getRed() * darkFactor), (int)(c.getBlue() * darkFactor),
                (int)(c.getGreen() * darkFactor), c.getAlpha());
    }

    double brightFactor = 1.1;
    public Color brighter(Color c){
        return new Color(Math.min((int)(c.getRed() * darkFactor), 255), Math.min((int)(c.getBlue() * darkFactor), 255),
                Math.min((int)(c.getGreen() * darkFactor), 255), c.getAlpha());
    }
}