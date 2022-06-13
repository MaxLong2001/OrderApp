package backend.CustomerException.Order;

import backend.Dish;

/**
 * 这个类是针对用户提交订单异常而建立的。
 * 当用户提交订单时菜品不存在，那么应该抛出这个异常。
 */

public class DishUndefined extends Exception{

    /**
     * 为了显示是哪一个菜品不存在，我们引用私有变量Dish
      */
    private final String dish;

    /**
     * 为了显示是哪一个菜品不存在，我们使用构造方法。
     * @param arg_dish 哪个菜品不存在
      */
    public DishUndefined(String arg_dish){
        dish = arg_dish;
    }

    // 如果存在异常，那么应该抛出这个语句
    @Override
    public String toString(){
        return "菜品" + dish + "不存在！！";
    }
}
