package frontend;

import backend.Customer;
import backend.Order;
import backend.Owner;
import backend.User;
import frontend.Customer.home.HomeView;
import frontend.Customer.home.OwnerArea;
import frontend.Customer.order.OrderView;
import frontend.Owner.OwnerView;
import frontend.Tool.MyEvent;
import frontend.Tool.MyListener;
import frontend.Tool.MyView;

import javax.swing.*;
import java.awt.*;

/**
 * 程序窗口
 */
public class Frontend extends JFrame{
    public static boolean deBug = false;

    static Customer loginCustomer;
    static Owner loginOwner;
//    public static Order currentOrder;
    public static  Owner currentOwner;

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

        if(deBug){
//            Customer customer = new Customer();

    //        nowView = new Welcome();
    //        nowView = new Order();
    //        nowView = new OwnerView();
    //        content.add(nowView);
//            JScrollPane scrollPane = new JScrollPane();
//            scrollPane.setViewportView(new Map());
//            add(scrollPane);
            Owner owner = new Owner();
            owner.setName("姜星如");

            Customer customer = new Customer();
            try{
                customer.setName("赵正阳");
            }catch (Exception e){}
//            changeView(new OrderView(null, null));
            changeView(new HomeView(customer));
            changeView(new OwnerView(owner));
        }else{
            changeView(new Welcome());
        }
        setVisible(true);
    }

    private void changeView(MyView newView){
        if(nowView != null) remove(nowView);
        nowView = newView;
        nowView.addMyListener(new ViewListener());
        add(nowView);
        setSize(nowView.getPreferredSize().width + 20, nowView.getPreferredSize().height + 40);
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
                User user = ((Login.DoneLoginEvent) e).getLoginUser();
                if(user instanceof Customer){
                    loginCustomer = (Customer) user;
                    loginOwner = null;
                    changeView(new HomeView((Customer)user));
                }else if (user instanceof Owner){
                    loginOwner = (Owner) user;
                    loginCustomer = null;
                    changeView(new OwnerView((Owner) user));
                }
            } else if (e instanceof Register.DoneRegisterEvent) {
                User user = ((Register.DoneRegisterEvent) e).getLoginUser();
                if(user instanceof Customer){
                    loginCustomer = (Customer) user;
                    loginOwner = null;
                    changeView(new HomeView((Customer)user));

                }else if (user instanceof Owner){
                    loginOwner = (Owner) user;
                    loginCustomer = null;
                    changeView(new OwnerView((Owner) user));

                }
            }else if (e instanceof OwnerArea.EnterOwner){
                currentOwner = ((OwnerArea.EnterOwner)e).owner;
                changeView(new OrderView(((OwnerArea.EnterOwner)e).owner));
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

    public static Customer getLoginCustomer() {
        return loginCustomer;
    }

    public static Owner getLoginOwner() {
        return loginOwner;
    }

}
