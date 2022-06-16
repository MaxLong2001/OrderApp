package backend.Recommend;

import backend.Order;

import java.util.Date;
import java.util.List;

/**
 * @version 1.0
 * @author JiangXingru
 * �������Ϊ���ڴ����û����ʱ���û��ı�������������
 */

public class ForOrder {

    /**
     * ��������˳�������̼Ҷ����б�
     * @param orders �̼Ҷ����б�
     */
    public static List<Order> OrderRecommend(List<Order> orders){

        // ���̼Ҷ���������������
        orders.sort(((o1, o2) -> {
            Date date1 = o1.orderTime;
            Date date2 = o2.orderTime;
            return date1.compareTo(date2);
        }));

        // ����������õĶ����б�
        return orders;
    }
}
