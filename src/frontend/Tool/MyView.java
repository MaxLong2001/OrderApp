package frontend.Tool;

import frontend.Tool.MyEvent;
import frontend.Tool.MyListener;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 基于swing的JPanel自定义的MyPanel
 * 额外具有的功能为抛出事件（内部使用）和添加事件监听器（外部使用）
 */
public class MyView extends JPanel {
    public MyView(){
        super();
//        setSize(1024, 300);
    }

    List<MyListener> eventListenerList = new ArrayList<MyListener>();

    /**
     * 手动抛出一个事件，如果先前存在添加了的事件监听器，则会使用那些监听器触发事件。
     * @param e 要抛出的事件
     */
    public void dispatchMyEvent(MyEvent e){
        for(MyListener listener :  eventListenerList){
            listener.handlerEvent(e);
        }
    }

    /**
     * 添加一个事件监听器，只监听该类内部抛出的事件。配合抛出事件方法使用。
     * @param eventListener 要添加的事件监听器
     */
    public void addMyListener(MyListener eventListener){
        eventListenerList.add(eventListener);
    }

    /**
     * 打开一个窗口
     */
    public static void openWindow(JPanel view, String title){
        JFrame jFrame = new JFrame();
        jFrame.setSize(new Dimension(view.getPreferredSize()));
        jFrame.setTitle(title);
        jFrame.getContentPane().add(view);
        jFrame.setVisible(true);
    }
}
