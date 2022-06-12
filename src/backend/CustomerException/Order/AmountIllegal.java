package backend.CustomerException.Order;

import backend.Dish;

/**
 * ����˿͵����Ʒ�����������˵�ǰ��Ʒ����������ô����ҲӦ���׳�һ���쳣��
 */

public class AmountIllegal extends Exception{

    /**
     * Ϊ��ȷ������һ����Ʒ�������������������ǽ�����˽�����ԡ�
     */
    private final Dish dish;
    private final int amount;

    /**
     * ���췽��
     * @param arg_dish �ĸ���Ʒ��������
     * @param amount ��ǰ�˿�ѡ��˲�Ʒ������
     */
    public AmountIllegal(Dish arg_dish, int amount){
        dish = arg_dish;
        this.amount = amount;
    }

    /**
     * ��д�쳣��������
     */
    @Override
    public String toString(){
        return "��Ʒ�������꣡��\n" + "��Ʒ��" + dish.name + "\n"
                + "������" + dish.getRemainQuantity() + "\n" +
                "�����ˣ�" + amount;
    }
}
