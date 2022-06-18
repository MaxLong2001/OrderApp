package frontend.Customer.home;

import backend.Owner;
import backend.User;
import frontend.Frontend;
import frontend.Tool.MyView;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class OwnerArea extends MyView {


    private List<Owner> owners;


    OwnerList ownerList;
    Box topArea;

    public OwnerArea(){
        setOwners();

        JLabel number = new JLabel();
        number.setText("为您找到 " + owners.size() + " 个商家");
        JPanel numberArea = new JPanel();
        numberArea.add(number);
        numberArea.setLayout(new FlowLayout(FlowLayout.LEFT));

        JComboBox type = new JComboBox();
//        type.setText("排序方式");
        JLabel type1 = new JLabel();
        type1.setText("综合排序");
        type.addItem("综合排序");

        JPanel typeArea = new JPanel();
        typeArea.setLayout(new FlowLayout(FlowLayout.RIGHT));
        typeArea.add(type);



        topArea = Box.createHorizontalBox();
        topArea.add(numberArea);
        topArea.add(typeArea);

        ownerList = new OwnerList(owners);

        Box vBox = Box.createVerticalBox();


        vBox.add(topArea);
        vBox.add(ownerList);

        add(vBox);
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
            // todo 获得商家列表
        }
    }


}
