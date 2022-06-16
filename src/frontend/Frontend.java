package frontend;

import frontend.Tool.MyEvent;
import frontend.Tool.MyListener;
import frontend.Tool.MyView;

import javax.swing.*;
import java.awt.*;

/**
 * 程序窗口
 */
public class Frontend extends JFrame{
    public static boolean deBug = true;


    MyView nowView;
    Container content = getContentPane();
    /**
     * 使用该构造方法直接构造一个购餐系统的图形化界面
     */
    public Frontend(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        FlowLayout layout = new FlowLayout();
//        layout.setAlignment(FlowLayout.CENTER);
//        setLayout(layout);
        setSize(1024, 768);
        setTitle("购餐系统");
//        nowView = new Welcome();
//        nowView = new Order();
//        nowView = new OwnerView();
//        content.add(nowView);
        changeView(new Welcome());
        setVisible(true);



    }

    private void changeView(MyView newView){
        if(nowView != null) remove(nowView);
        nowView = newView;
        nowView.addMyListener(new ViewListener());
        add(nowView);
        setSize(nowView.getPreferredSize().width, nowView.getPreferredSize().height + 40);
        validate();
    }

    class ViewListener implements MyListener{
        @Override
        public void handlerEvent(MyEvent e) {
            if(e instanceof Welcome.ToLogin){
                changeView(new Login());
            }else if(e instanceof Welcome.ToRegister){
                changeView(new Register());
            }else if(e instanceof Login.DoneLoginEvent){
                changeView(new OwnerView(((Login.DoneLoginEvent) e).getLoginUser()));
            }
        }
    }


    class DoneLoginListener implements MyListener {
        @Override
        public void handlerEvent(MyEvent e){
//            content.remove(loginView);
//            invalidate();
//            content.add(new Welcome());
//            revalidate();
//            invalidate();
//            repaint();
        }
    }


}
