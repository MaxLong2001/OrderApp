package frontend.Customer.home;

import backend.Owner;
import frontend.Tool.MyButton;
import frontend.Tool.MyItem;
import frontend.Tool.MyList;

import javax.swing.*;
import java.util.List;

public class OwnerList extends MyList {

    public OwnerList(List<Owner> owners){
        for (Owner owner : owners){
            OwnerItem ownerItem = new OwnerItem(owner);
            addItem(ownerItem);
        }
        setHeight(400);
    }

}
class OwnerItem extends MyItem {
    int itemWidth = 400;
    public MyButton enter;
    public Owner owner;

    public OwnerItem(Owner owner){
        this.owner = owner;
        getNameLabel().setText(owner.getName());
        getIntroductionArea().setText(owner.getIntroduction());

        enter = new MyButton("进入商家");

        addRight(enter);
    }
}