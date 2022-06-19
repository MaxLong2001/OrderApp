package backend.Recommend;

import backend.Order;

import java.util.Date;
import java.util.List;

/**
 * @version 1.0
 * @author JiangXingru
 * 这个类是为了在创建用户表的时候将用户的表单按照日期排序
 */

public class ForOrder {

    /**
     * 按照日期顺序排序商家订单列表
     * @param orders 商家订单列表
     */
    public static void OrderRecommend(List<Order> orders){

        // 将商家订单按照日期排序
        orders.sort(((o1, o2) -> {
            Date date1 = o1.orderTime;
            Date date2 = o2.orderTime;
            return date2.compareTo(date1);
        }));

    }
}
