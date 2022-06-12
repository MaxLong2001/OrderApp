package backend.CustomerException.Order;

import backend.Dish;

/**
 * 如果顾客点击菜品的数量超过了当前菜品的数量，那么我们也应该抛出一个异常。
 */

public class AmountIllegal extends Exception{

    /**
     * 为了确定是哪一个菜品数量超过了总量，我们建立了私有属性。
     */
    private final Dish dish;
    private final int amount;

    /**
     * 构造方法
     * @param arg_dish 哪个菜品数量超额
     * @param amount 当前顾客选择此菜品的数量
     */
    public AmountIllegal(Dish arg_dish, int amount){
        dish = arg_dish;
        this.amount = amount;
    }

    /**
     * 重写异常呼出方法
     */
    @Override
    public String toString(){
        return "菜品数量超标！！\n" + "菜品：" + dish.name + "\n"
                + "数量：" + dish.getRemainQuantity() + "\n" +
                "您点了：" + amount;
    }
}
