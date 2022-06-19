package frontend.Customer.home;

import backend.Owner;
import backend.Recommend.ForCustomer;
import backend.User;
import database.Database;
import frontend.Frontend;
import frontend.Owner.ContentArea;
import frontend.Tool.MyEvent;
import frontend.Tool.MyItem;
import frontend.Tool.MyView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class OwnerArea extends MyView {


    private List<Owner> owners;


    OwnerList ownerList;
    Box topArea;

    public OwnerArea(){
        setOwners();


        JLabel number = new JLabel();
        number.setText("为您推荐 " + owners.size() + " 个商家");
        JPanel numberArea = new JPanel();
        numberArea.add(number);
        numberArea.setLayout(new FlowLayout(FlowLayout.LEFT));

//        JComboBox type = new JComboBox();
//        type.setText("排序方式");
//        JLabel type1 = new JLabel();
//        type1.setText("综合排序");
//        type.addItem("综合排序");

        JPanel typeArea = new JPanel();
        typeArea.setLayout(new FlowLayout(FlowLayout.RIGHT));
//        typeArea.add(type);



        topArea = Box.createHorizontalBox();
        topArea.add(numberArea);
        topArea.add(typeArea);

        ownerList = new OwnerList(owners);

        Box vBox = Box.createVerticalBox();


        vBox.add(topArea);
        vBox.add(ownerList);

        add(vBox);


//        List<JPanel> ownerItems = ownerList.getItems();
//        for(JPanel item : ownerItems){
//            ((OwnerItem)item).enter.addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//                    dispatchMyEvent(new EnterOwner(((OwnerItem) item).owner));
//                }
//            });
//        }

    }

    public void setOwners(){
        if(Frontend.deBug){
            Owner owner = new Owner();
            owner.setRating(4.5);
            owner.setName("黑猫厨房");
            owner.setIntroduction("黑猫厨房黑猫厨房黑猫厨房黑猫厨房黑猫厨房黑猫厨房黑猫厨房");
            owners = new ArrayList<>();
            owners.add(owner);
            owners.add(owner);
            owners.add(owner);
            owners.add(owner);
        }else {
            try{
                owners = ForCustomer.OwnerRecommend(Database.getAllOwner());
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public static class EnterOwner extends MyEvent{
        public Owner owner;
        EnterOwner(Owner owner){
            this.owner = owner;
        }
    }

}
