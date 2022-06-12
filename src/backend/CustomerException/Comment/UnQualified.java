package backend.CustomerException.Comment;

/**
 * 这个类的用法是，如果用户在相应商家中没有吃过饭，那么就应该抛出它
 */

public class UnQualified extends Exception{

    /**
     * 发生了相应异常应该打印
     */
    @Override
    public String toString(){
        return "您还没有在此商家用餐！！";
    }
}
