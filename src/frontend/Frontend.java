package frontend;

import javax.swing.*;
import java.awt.*;

/**
 * 程序窗口
 */
public class Frontend extends JFrame{
    MyView nowView;
    Container content = getContentPane();
    /**
     * 使用该构造方法直接构造一个购餐系统的图形化界面
     */
    public Frontend(){
//        FlowLayout layout = new FlowLayout();
//        layout.setAlignment(FlowLayout.CENTER);
//        setLayout(layout);
        setSize(1024, 768);
        setTitle("购餐系统");
        nowView = new Welcome();
        nowView = new Order();
        content.add(nowView);
        setVisible(true);
        nowView.addMyListener(new MyListener() {
            @Override
            public void handlerEvent(MyEvent e) {
                if(e instanceof Welcome.ToLogin){
                    changeView(new Login());
                }else if(e instanceof Welcome.ToRegister){
                    changeView(new Register());
                }
            }
        });
    }

    void changeView(MyView newView){
        remove(nowView);
        nowView = newView;
        add(nowView);
        validate();
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
