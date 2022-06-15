package backend;

import backend.AppException.AppException;
import database.Database;

import java.sql.SQLException;

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
        if(newUser instanceof Customer) {

            // 将用户插入数据库
            try{
                Database.insertCustomer(newUser.getName(), newUser.getPassword());
            }catch(SQLException e){
              throw new AppException("数据库插入顾客异常！！");
            }
        }
        if(newUser instanceof Owner){
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

    /**
     * 用户输入用户名时实时监测
     * @param name 输入姓名信息
     * @throws AppException 用户名格式异常
     */
    public void setName(String name) throws AppException{
        Constraint.CheckUname(name);
        this.name = name;
    }

    /**
     * 用户输入密码实时检测
     * @param password 输入密码信息
     * @throws AppException 密码格式异常
     */
    public void setPassword(String password) throws AppException {
        Constraint.CheckPwd(password);
        this.password = password;
    }
}
