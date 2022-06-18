package frontend.Owner;

import backend.Dish;
import backend.Order;
import frontend.DishItem;
import frontend.Tool.MyList;

import java.util.List;

public class DishList extends MyList {
    List<Dish> dishes;

    public DishList(List<Dish> dishes) {
        this.dishes = dishes;

        for(Dish dish : dishes){
            DishItem dishItem = new DishItem(DishItem.ownerBrowse, dish, 0);
            addItem(dishItem);
        }
        setHeight(400);
    }
}
