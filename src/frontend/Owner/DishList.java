package frontend.Owner;

import backend.Dish;
import backend.Order;
import frontend.DishItem;
import frontend.Tool.MyButton;
import frontend.Tool.MyList;

import javax.swing.*;
import java.util.List;

public class DishList extends JPanel {
    List<Dish> dishes;

    MyList content;

    MyButton newDish;

    public DishList(List<Dish> dishes) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        newDish = new MyButton("添加菜品");

        content = new MyList();

        this.dishes = dishes;

        for(Dish dish : dishes){
            DishItem dishItem = new DishItem(DishItem.ownerBrowse, dish, 0);
            content.addItem(dishItem);
        }
        content.setHeight(400);

        add(newDish);
        add(content);
    }

    public MyButton getNewDishBtn() {
        return newDish;
    }
}
