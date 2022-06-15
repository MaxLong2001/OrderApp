package backend;

import backend.AppException.AppException;

import java.util.ArrayList;
import java.util.HashMap;

public class Owner extends User{
    public String introduction;
    public double rating;
    public ArrayList<Order> orders = new ArrayList<>();
    public ArrayList<Dish> dishes = new ArrayList<>();
    public ArrayList<String> comments = new ArrayList<>();

    /**
     * 这个方法用于商家添加菜品，传入对应的菜品
     * 添加成功则返回true
     */
    public boolean addDishes(Dish dish)
    {
        int i=0;
        for(i=0; i<dishes.size(); i++)
        {
            if(dishes.get(i).equals(dish)) {
                return false;
            }
        }
        dishes.add(dish);
        return true;
    }

    /**
     * 这个方法用于商家修改菜品，传入对应的菜品
     * 修改成功则返回true
     */
    public boolean modifyDishes(Dish dish)
    {
        int i=0;
        for(i=0; i<dishes.size(); i++)
        {
            if(dishes.get(i).equals(dish)) {
                dishes.set(i,dish);
                return true;
            }
        }
        return false;
    }

    /**
     * 这个方法用于商家下架菜品，传入对应的菜品
     * 查找到并删除成功则返回true
     */
    public boolean pullOffDishes(Dish dish)
    {
        int i=0;
        for(i=0; i<dishes.size(); i++)
        {
            if(dishes.get(i).equals(dish)) {
                dishes.remove(i);
                return true;
            }
        }
        return false;
    }



    /**
     * 这个方法用于获取商家的名称
     */
    public String getName() {
        return name;
    }

    /**
     * 这个方法用于修改商家的名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 这个方法用于获取商家的简介
     */
    public String getIntroduction() {
        return introduction;
    }

    /**
     * 这个方法用于修改商家的简介
     */
    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    /**
     * 这个方法用于返回商家的评分
     */
    public double getRating() {
        return rating;
    }

    /**
     * 这个方法用于修改商家的评分
     */
    public void setRating(double rating) {
        this.rating = rating;
    }

    @Override
    public void modifyName(String newName) throws AppException {
        //todo
    }

    @Override
    public void modifyPwd(String newPwd) throws AppException {
        //todo
    }
}
