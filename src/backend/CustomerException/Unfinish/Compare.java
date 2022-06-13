package backend.CustomerException.Unfinish;

/**
 * @version 1.0
 * @author JiangXingru
 * 这是一个判断订单中商家的名字是否和现在商家名字相同的类
 * 如果相同，抛出一个订单未完成异常
 */

public class Compare {

    /**
     * 这个方法用于判断两个名字是否相同
     * @param a 名字a
     * @param b 名字b
     * @throws UnfinishedException 未定义异常
     */
    public static void compare(String a, String b) throws UnfinishedException {
        if(a.equals(b)){
            throw new UnfinishedException();
        }
    }
}
