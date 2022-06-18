package backend;

import backend.AppException.AppException;
import backend.Monitor.Constraint;
import database.Database;

import java.sql.SQLException;
import java.util.Map;

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
    static public User login(String name, String pwd) throws AppException{

        // 临时用户信息变量
        Map<String, String> temp_all;

        // 从数据库中获取<用户类型， 密码键值对>
        try{
            temp_all = Database.getPassword(name);
        }catch (SQLException e){
            throw new AppException("数据库查询信息异常！！");
        }

        // 如果是顾客类型、商家类型，进行新用户生成
        if(temp_all.containsKey("customer")){

            // 如果密码正确
            if(temp_all.get("customer").equals(pwd)){

                // 返回顾客类型变量
                return new Customer(name, pwd);
            } else{

                throw new AppException("密码不正确！！");
            }
        } else if(temp_all.containsKey("owner")){

            // 如果密码正确
            if(temp_all.get("owner").equals(pwd)){

                // 返回商家类型变量
                return new Owner().NewOwner(name, pwd);
            } else{

                throw new AppException("密码不正确！！");
            }
        } else {
            throw new AppException("所查找对象不属于任何类！！");
        }
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

            // 将商家插入数据库
            try{
                Database.insertOwner(newUser.getName(), ((Owner) newUser).getIntroduction(), newUser.getPassword());
            }catch(SQLException e){
                throw new AppException("数据库插入顾客异常！！");
            }
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
