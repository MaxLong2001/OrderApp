package backend.CustomerException.Order;

import backend.Dish;

import java.util.List;

/**
 * ����������׳������쳣�����ж�
 */

public class SettleAll{

    /**
     * ����������������Ʒ�Ƿ����
     * @param arg_dishes �̼Ҳ�Ʒ�б�
     * @param name �û����Ʒ��
     * @throws DishUndefined ��Ʒδ�����쳣
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
     * ���������������û���Ĳ�Ʒ�Ƿ����
     * @param arg_dishes ��ǰ�̼Ҳ�Ʒ��
     * @param name �û����Ʒ��
     * @param amount �û��������
     * @throws AmountIllegal �������Ϸ��쳣
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
