package backend;

import java.util.ArrayList;
import java.util.HashMap;

public class Order {
    public HashMap<String,Integer> dishes;
    public String nameOfCustomer;
    public String nameOfOwner;
    public double price;
    public boolean status;

    /**
     * 这个方法用于返回菜品列表和对应的数量
     */
    public HashMap<String, Integer> getDishes() {
        return dishes;
    }

    /**
     * 这个方法用于设置菜品列表和对应的数量
     */
    public void setDishes(HashMap<String, Integer> dishes) {
        this.dishes = dishes;
    }

    /**
     * 这个方法用来返回订单的用户名称
     */
    public String getNameOfCustomer() {
        return nameOfCustomer;
    }

    /**
     * 这个方法用来设置订单的用户名称
     */
    public void setNameOfCustomer(String nameOfCustomer) {
        this.nameOfCustomer = nameOfCustomer;
    }

    /**
     * 这个方法用来返回订单的商家名称
     */
    public String getNameOfOwner() {
        return nameOfOwner;
    }

    /**
     * 这个方法用来设置订单的商家名称
     */
    public void setNameOfOwner(String nameOfOwner) {
        this.nameOfOwner = nameOfOwner;
    }


    /**
     * 这个方法用来返回订单的价格
     */
    public double getPrice() {
        return price;
    }

    /**
     * 这个方法用来设置订单的价格
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * 这个方法用来查看订单是否完成
     */
    public boolean isStatus() {
        return status;
    }

    /**
     * 这个方法用来改变订单是否完成的状态
     */
    public void setStatus(boolean status) {
        this.status = status;
    }
}