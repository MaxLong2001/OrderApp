package backend.CustomerException.Order;

import backend.Dish;

import java.util.List;

/**
 * 这个类用于抛出各种异常进行判断
 */

public class SettleAll{

    /**
     * 用这个方法来检验菜品是否存在
     * @param arg_dishes 商家菜品列表
     * @param name 用户点菜品名
     * @throws DishUndefined 菜品未定义异常
     */
    public static void TestExist(List<Dish> arg_dishes, String name) throws DishUndefined{
        int i;
        for(i = 0; i < arg_dishes.size(); i++){
            Dish temp = arg_dishes.get(i);
            if(temp.name.equals(name)){
                break;
            }
        }
        if(i == arg_dishes.size()){
            throw new DishUndefined(name);
        }
    }

    /**
     * 这个方法用来检测用户点的菜品是否过量
     * @param arg_dishes 当前商家菜品表
     * @param name 用户点菜品名
     * @param amount 用户点菜数量
     * @throws AmountIllegal 数量不合法异常
     */
    public static
    void TestAmount(List<Dish> arg_dishes, String name, int amount)
    throws AmountIllegal{
        for (Dish temp : arg_dishes) {
            if (temp.name.equals(name)) {
                if (amount > temp.getRemainQuantity()) {
                    throw new AmountIllegal(temp, amount);
                }
            }
        }
    }
}
