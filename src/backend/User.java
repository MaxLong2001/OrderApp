package backend;

import backend.AppException.AppException;

/**
 * 用户基类，包括该系统可能用户的公共基本信息和公共方法
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
    public static void register(User newUser) throws AppException{
        if(newUser instanceof Customer){
            //todo 这部分由负责Customer的完成

        }else if(newUser instanceof Owner){
            //todo 这部分由负责Owner的完成
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

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public void setName(String name) {
        //todo 加入约束
        this.name = name;
    }

    public void setPassword(String password) {
        //todo 加入约束
        this.password = password;
    }

    /**
     * 如果Name不合格则返回一个“xxx不合格”的异常
     * @throws AppException 一个“用户名不合规”的异常
     */
    public void checkName() throws AppException {
        //todo
    }

    /**
     * 如果Name不合格则返回一个“xxx不合格”的异常
     * @throws AppException 一个“密码不合规”的异常
     */
    public void checkPwd() throws AppException {
        //todo
    }

}
