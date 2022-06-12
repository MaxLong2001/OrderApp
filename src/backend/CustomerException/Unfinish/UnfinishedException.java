package backend.CustomerException.Unfinish;

/**
 * @version 1.0
 * @author JiangXingru
 * 这个类用于用户抛出订单未完成异常,
 * 如果用户有未完成订单，那么抛出一个未完成订单异常
 */

public class UnfinishedException extends Exception{

    /**
     * 重写打印异常方法
     */
    @Override
    public String toString(){
        return "您在本商家还有订单未完成！";
    }
}
