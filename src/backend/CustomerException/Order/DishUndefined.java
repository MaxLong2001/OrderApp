package backend.CustomerException.Order;

import backend.Dish;

/**
 * �����������û��ύ�����쳣�������ġ�
 * ���û��ύ����ʱ��Ʒ�����ڣ���ôӦ���׳�����쳣��
 */

public class DishUndefined extends Exception{

    /**
     * Ϊ����ʾ����һ����Ʒ�����ڣ���������˽�б���Dish
      */
    private final String dish;

    /**
     * Ϊ����ʾ����һ����Ʒ�����ڣ�����ʹ�ù��췽����
     * @param arg_dish �ĸ���Ʒ������
      */
    public DishUndefined(String arg_dish){
        dish = arg_dish;
    }

    // ��������쳣����ôӦ���׳�������
    @Override
    public String toString(){
        return "��Ʒ" + dish + "�����ڣ���";
    }
}
