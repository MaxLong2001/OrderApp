package frontend.Customer;

import backend.Owner;
import backend.User;
import frontend.Frontend;
import frontend.Tool.MyColor;
import frontend.Tool.MyItem;
import frontend.Tool.MyView;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 顾客登陆后进入的界面，对于商家进行一个展示。
 */
public class OwnerView extends MyView {

    private User loginUser;

    private List<Owner> owners;


    OwnerList ownerList;
    Box topArea;

    public OwnerView(User loginUser){
        this.loginUser = loginUser;
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

    class OwnerList extends JPanel {
        private int listWidth = 400;
        private int listHeight = 400;

        public OwnerList(List<Owner> owners){
            Box vBox = Box.createVerticalBox();
            for (Owner owner : owners){
                vBox.add(new OwnerItem(owner));
            }
            add(vBox);
        }
        /**
         * 菜品列表项。包括的内容有：菜品的名称、菜品的剩余数量、一个加号按钮。
         */
        class OwnerItem extends MyItem {
            int itemWidth = 400;

            public OwnerItem(Owner owner){
                getNameLabel().setText(owner.getName());
                getIntroductionArea().setText(owner.getIntroduction());

                JButton enter = new JButton();
                enter.setText("进入商家");

                addRight(enter);
            }

        }
    }
}
