package backend.CustomerException.Unfinish;

import backend.Order;

/**
 * @version 1.0
 * @author JiangXingru
 * 这个类的创建是想给前端返回更加详细的未完成订单信息；
 * 包括未完成订单本身和一个提示语{@link UnfinishedException}
 */

public class DetailInDetail {

    // 提示语
    public String warning;

    // 未完成的订单
    public Order missing;
}
