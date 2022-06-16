package backend;

import backend.AppException.AppException;
import backend.Monitor.Constraint;
import database.Database;

import java.sql.SQLException;
import java.util.*;

public class Owner extends User{
    public String introduction;
    public double rating;
    public int visit;
    public ArrayList<Order> orders_finished = new ArrayList<>();
    public ArrayList<Order> orders_unfinished = new ArrayList<>();
    public List<Dish> dishes = new ArrayList<>();
    public ArrayList<String> comments = new ArrayList<>();

    /**
     * 这个初始化方法可以初始化商家的基本属性。
     * 这个方法中传入的参数来自登录界面中的用户名。
     * 通过用户名向数据库请求相关数据
     * 订单分成已完成订单和未完成订单列表。
     * 注：这个方法应该在图形化登录时使用。
     * @throws AppException 数据库异常
     */
    public Owner NewOwner(String name, String password) throws AppException {

        //这是一个临时设置的owner对象，用于保存从数据库中获得的owner
        Owner owner = new Owner();

        // 这是一个临时设置的密码变量
        Map <String, String>temp_all;

        // 在这里将顾客的用户名设为登录注册页面传入的用户名。
        this.name = name;

        // 首先先检验用户名是否存在
        try{
            temp_all = Database.getPassword(name);
        }catch (SQLException e){
            throw new AppException("数据库错误！！");
        }catch (AppException e){
            this.name = null;
            throw new AppException("用户名不存在！！");
        }

        if(!temp_all.containsKey("owner")){
            throw new AppException("用户名属于用户！！");
        }

        // 如果获得密码与现密码不符
        if(!temp_all.get("owner").equals(password)){
            throw new AppException("密码错误！！");
        }

        // 尝试获取商家信息
        try{
            owner = Database.getOwner(name);
        }catch (SQLException e){
            throw new AppException("获取商家信息失败！！");
        }

        return owner;
    }
        /**
         * 这个方法用于商家添加菜品，传入对应的菜品
         * 添加成功则返回true
         */
    public void addDishes(String nameOfOwner, String nameOfDish, double price, String type, String introduction) throws AppException {
        int i=0;
        try {
            dishes = Database.getDishList(nameOfOwner);
        }catch (SQLException e)
        {
            throw new AppException("获取菜品列表失败！！");
        }

        //临时变量dish存储输入的参数
        Dish dish = new Dish();
        dish.name = nameOfDish;
        dish.price = price;
        dish.type = type;
        dish.introduction = introduction;

        //记录数据库中是否有dish
        int flag=0;
        for(i=0; i<dishes.size(); i++)
        {
            if(dishes.get(i).getName().equals(dish.name)) {
                flag = 1;
            }
        }
        if(flag==0)
        dishes.add(dish);
        else throw new AppException("菜品重复！！");

        try {
            Database.insertDish(dish,nameOfOwner);
        }catch (SQLException e){
            throw new AppException("添加菜品内容失败！！");
        }
    }

    /**
     * 这个方法用于商家修改菜品，传入对应的菜品
     * 修改成功则返回true
     */
    public void modifyDishes(String nameOfOwner, String nameOfDish, double price, String type, String introduction) throws AppException {
        int i = 0;
        try {
            dishes = Database.getDishList(nameOfOwner);
        }catch (SQLException e)
        {
            throw new AppException("获取菜品列表失败！！");
        }

        //临时变量dish存储输入的参数
        Dish dish = new Dish();
        dish.name = nameOfDish;
        dish.price = price;
        dish.type = type;
        dish.introduction = introduction;

        //记录数据库中是否有dish
        int flag=0;
        for(i=0; i<dishes.size(); i++)
        {
            if(dishes.get(i).getName().equals(dish.name)) {
                flag = 1;
            }
        }
        if(flag==1)
            dishes.set(i,dish);
        else throw new AppException("不存在这样的菜品！！");

        try {
            Database.changeDish(nameOfOwner,dish);
        }catch (SQLException e){
            throw new AppException("更新菜品内容失败！！");
        }
    }

    /**
     * 这个方法用于商家下架菜品，传入对应的菜品
     * 查找到并删除成功则返回true
     */
    public void pullOffDishes(String nameOfOwner, String nameOfDish, double price, String type, String introduction) throws AppException {
        int i = 0;
        try {
            dishes = Database.getDishList(nameOfOwner);
        }catch (SQLException e)
        {
            throw new AppException("获取菜品列表失败！！");
        }

        //临时变量dish存储输入的参数
        Dish dish = new Dish();
        dish.name = nameOfDish;
        dish.price = price;
        dish.type = type;
        dish.introduction = introduction;

        //记录数据库中是否有dish
        int flag = 0;
        for(i=0; i<dishes.size(); i++)
        {
            if(dishes.get(i).getName().equals(dish.name)) {
                flag = 1;
            }
        }
        if(flag == 1)
            dishes.remove(i);
        else throw new AppException("不存在这样的菜品！！");

        try {
            Database.deleteDish(nameOfOwner,dish);
        }catch (SQLException e){
            throw new AppException("删除菜品内容失败！！");
        }

    }



    /**
     * 这个方法用于获取商家的名称
     */
    public String getName() {
        return name;
    }

    /**
     * 这个方法用于修改商家的名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 这个方法用于获取商家的简介
     */
    public String getIntroduction() {
        return introduction;
    }

    /**
     * 这个方法用于修改商家的简介
     */
    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    /**
     * 这个方法用于返回商家的评分
     */
    public double getRating() {
        return rating;
    }

    /**
     * 这个方法用于修改商家的评分
     */
    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getVisit() {
        return visit;
    }

    public void setVisit(int visit) {
        this.visit = visit;
    }

    /**
     * 如果商家想要查看自己已完成订单的话，那么我们可以提供商家已完成的订单列表。
     * 注：按照时间排序(最近的在前）
     */
    public List<Order> ShowFinished(){
        orders_finished.sort(new Comparator<Order>() {
            public int compare(Order o1, Order o2) {
                try {
                    if (o1.getOrderTime() == null || o2.getOrderTime() == null) {
                        return 1;
                    }
                    Date dt1 = o1.getOrderTime();
                    Date dt2 = o2.getOrderTime();

                    if (dt1.getTime() < dt2.getTime()) {
                        return -1;
                    } else if (dt1.getTime() > dt2.getTime()) {
                        return 1;
                    } else {
                        return 0;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return 0;
            }
        });
        return orders_finished;
    }

    /**
     * 如果商家想要查看自己未完成订单的话，那么我们可以提供商家未完成的订单列表。
     * 注：按照时间排序(最近的在前）
     */

    public List<Order> ShowUnFinished(){
        orders_unfinished.sort(new Comparator<Order>() {
            public int compare(Order o1, Order o2) {
                try {
                    if (o1.getOrderTime() == null || o2.getOrderTime() == null) {
                        return 1;
                    }
                    Date dt1 = o1.getOrderTime();
                    Date dt2 = o2.getOrderTime();

                    if (dt1.getTime() < dt2.getTime()) {
                        return -1;
                    } else if (dt1.getTime() > dt2.getTime()) {
                        return 1;
                    } else {
                        return 0;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return 0;
            }
        });
        return orders_unfinished;
    }
    @Override
    public void modifyName(String newName) throws AppException {

        // 检验用户名格式
        Constraint.CheckUname(newName);

        // 将用户名插入数据库
        try{
            Database.changeOwnerName(this.name, newName);
        }catch (SQLException e){
            throw new AppException("数据库异常！！");
        }

        this.name = newName;
    }

    @Override
    public void modifyPwd(String newPwd) throws AppException {

        // 检验密码格式
        Constraint.CheckPwd(newPwd);

        // 将新密码写入数据库
        try{
            Database.changeOwnerPassword(this.name, newPwd);
        }catch (SQLException e){
            throw new AppException("数据库异常！！");
        }

        this.password = newPwd;
    }
}
