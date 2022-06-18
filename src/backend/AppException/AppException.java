package backend.AppException;

/**
 * @version 1.0
 * @author JiangXingru
 * 应组长要求，这个类用来将”生硬“的异常转化成我们比较通俗易懂的异常。
 */

public class AppException extends Exception{

    /**
     * 这个类需要一个通俗易懂的标识符，
     * 需要用一个私有属性来表示
     */
    private final String warning;

    /**
     * 在构造方法中输入你想要的字符串，将字符串保存到私有属性中
     * @param arg_warning 用户自定义的警告变量
     */
    public AppException(String arg_warning){
        warning = arg_warning;
    }

    /**
     * 这个类用来输出我们想要的异常对象
     */
    @Override
    public String toString(){
        return this.warning;
    }
}
