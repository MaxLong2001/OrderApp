package frontend.Tool;

import backend.Dish;
import backend.Order;
import frontend.DishItem;

import java.util.ArrayList;
import java.util.List;

/**
 * 已经存在滚动条
 */
public class DishList extends MyList {
    List<Dish> dishes;
    Order order;

    public List<DishItem> dishItems = new ArrayList<>();

    public DishList(List<Dish> dishes, Order order){
        this.dishes = dishes;
        this.order = order;

        for(Dish d : dishes){
            DishItem dishItem;
            int orderedNum;
            if(order != null){
                if(order.dishes.get(d.getName()) == null)orderedNum = 0;
                else orderedNum = order.dishes.get(d.getName());
            }else{
                orderedNum = 0;
            }
            dishItem = new DishItem(DishItem.customerBrowse, d, orderedNum);
            addItem(dishItem);
            dishItems.add(dishItem);
        }

        setHeight(400);
    }
    public DishList(List<Dish> dishes){
        this.dishes = dishes;

        for(Dish d : dishes){
            DishItem dishItem;
            int orderedNum;
            if(order != null){
                if(order.dishes.get(d.getName()) == null)orderedNum = 0;
                else orderedNum = order.dishes.get(d.getName());
            }else{
                orderedNum = 0;
            }
            dishItem = new DishItem(DishItem.ownerBrowse, d, orderedNum);
            addItem(dishItem);
        }

        setHeight(400);
    }


}
