package backend.CustomerException.Order;

/**
 * 这个类是为了判断订单提交时的异常而建立的。
 * 如果发现订单中顾客的名字不存在，则抛出此异常。
 */

public class UserUndefined extends Exception{

    // 定义打印异常方法
    @Override
    public String toString(){
        return "用户未定义！！！";
    }
}
