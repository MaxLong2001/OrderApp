package backend;

import backend.AppException.AppException;

/**
 *
 */
public abstract class User {
    protected String name;
    protected String password;


    /**
     * 输入用户名和密码来登录
     * @return 返回的User可能是Customer和Owner中的一种
     */
    static public User login(String name, String pwd){
        return null;
    }

    /**
     * 通过构造的对象（尚未存入数据库）来进行注册，即存入数据库
     * @param newUser 一个已经包括了该用户基本信息的对象，但是尚未存入数据库
     */
    public static void register(User newUser){
        if(newUser instanceof Customer){

        }else{

        }
    }


    /**
     * 抽象方法，需要Customer类和Owner类去实现
     * @param newName 要修改成的新用户名
     */
    public abstract void modifyName(String newName)throws AppException;
    /**
     * 抽象方法，需要Customer类和Owner类去实现
     * @param newPwd 要修改成的新密码
     */
    public abstract void modifyPwd(String newPwd)throws AppException;


}
