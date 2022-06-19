package backend;

import backend.AppException.AppException;
import backend.Monitor.Constraint;
import database.Database;

import java.sql.SQLException;
import java.util.*;
import backend.Recommend.*;

public class Owner extends User{
    public String introduction;
    public double rating;
    public int visit;
    public ArrayList<Order> orders_cooked = new ArrayList<>();
    public ArrayList<Order> orders_uncooked = new ArrayList<>();
    public List<Dish> dishes = new ArrayList<>();
    public ArrayList<Comment> comments = new ArrayList<>();


    public Owner(){

    }
    /**
     * 这个初始化方法可以初始化商家的基本属性。
     * 这个方法中传入的参数来自登录界面中的用户名。
     * 通过用户名向数据库请求相关数据
     * 订单分成已完成订单和未完成订单列表。
     * 注：这个方法应该在图形化登录时使用。
     * @throws AppException 数据库异常
     */
    public Owner(String name, String password) throws AppException {

        //这是一个临时设置的owner对象，用于保存从数据库中获得的owner
        Owner owner;

        // 这是一个临时设置的密码变量
        Map<String, String> temp_all;

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
        if(!temp_all.get("password").equals(password)){
            throw new AppException("密码错误！！");
        }

        // 尝试获取商家信息
        try{
            owner = Database.getOwner(name);
        }catch (SQLException e){
            throw new AppException("获取商家信息失败！！");
        }
        this.name = owner.name;
        this.introduction = owner.introduction;
        this.rating = owner.rating;
        this.visit = owner.visit;

        List<Order> tmp_orders;
        List<Dish> tmp_dishes;
        List<Comment> tmp_comments;

        // 尝试导出订单列表。
        try{
            tmp_orders = Database.getOwnerOrderList(name);
        }catch(SQLException e){
            throw new AppException("导出商家订单列表失败！！");
        }

        // 初始化orders_cooked和orders_uncooked
        this.orders_cooked = new ArrayList<>();
        this.orders_uncooked = new ArrayList<>();
        // 将导出的订单列表按照是否完成进行分类
        for(Order order: tmp_orders){
            if(order.completed&&order.cooked){
                this.orders_cooked.add(order);
            }
            else if(order.completed&&!order.cooked){
                this.orders_uncooked.add(order);
            }
        }
        ForOrder.OrderRecommend(orders_cooked);
        ForOrder.OrderRecommend(orders_uncooked);

        // 尝试导出菜品列表。
        try{
            tmp_dishes = Database.getDishList(name);
        }catch(SQLException e){
            throw new AppException("导出商家菜品列表失败！！");
        }

        this.dishes = new ArrayList<>();
        this.dishes.addAll(tmp_dishes);

        // 尝试导出评论列表。
        try{
            tmp_comments = Database.getOwnerComments(name);
        }catch(SQLException e){
            throw new AppException("导出商家评论列表失败！！");
        }

        this.comments = new ArrayList<>();
        this.comments.addAll(tmp_comments);

    }
        /**
         * 这个方法用于商家添加菜品，传入对应的菜品
         * 添加成功则返回true
         */
    public void addDishes(String nameOfOwner, String nameOfDish, double price, String type, String introduction) throws AppException {
        int i;
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
                break;
            }
        }
        if(flag==0)
        this.dishes.add(dish);
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
    public void modifyDishes(String nameOfOwner, String oldNameOfDish, String newNameOfDish, double price, String type, String introduction) throws AppException {
        int i;
        try {
            dishes = Database.getDishList(nameOfOwner);
        }catch (SQLException e)
        {
            throw new AppException("获取菜品列表失败！！");
        }

        if(newNameOfDish==""||price==0||type==""||introduction=="")
            throw new AppException("无法修改！！");

        //临时变量dish存储输入的参数
        Dish dish = new Dish();
        dish.name = newNameOfDish;
        dish.price = price;
        dish.type = type;
        dish.introduction = introduction;

        //记录数据库中是否有dish
        int flag=0;
        for(i=0; i<dishes.size(); i++)
        {
            if(dishes.get(i).getName().equals(oldNameOfDish)) {
                flag = 1;
                break;
            }
        }
        if(flag==1)
            this.dishes.set(i,dish);
        else throw new AppException("不存在这样的菜品！！");

        try {
            Database.changeDish(nameOfOwner,oldNameOfDish,dish);
        }catch (SQLException e){
            throw new AppException("更新菜品内容失败！！");
        }
    }

    /**
     * 这个方法用于商家将对应菜品数量+1，传入商家名称和菜品名称
     */
    public void addDishQuantity(String ownerName, String dishName) throws AppException {
        try {
            Database.addDishQuantity(ownerName,dishName);
        }catch (SQLException e)
        {
            throw new AppException("添加菜品失败！！");
        }
    }

    /**
     * 这个方法用于访问商家，传入商家名后将访问量+1
     */
    public void addVisit(String ownerName) throws AppException{
        try {
            Database.visitOwner(ownerName);
        }catch (SQLException e)
        {
            throw new AppException("访问商家失败！！");
        }
    }


    /**
     * 这个方法用于商家下架菜品，传入对应的菜品
     * 查找到并删除成功则返回true
     */
    public void pullOffDishes(String nameOfOwner, String nameOfDish, double price, String type, String introduction) throws AppException {
        int i;
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
                break;
            }
        }
        if(flag == 1)
            this.dishes.remove(i);
        else throw new AppException("不存在这样的菜品！！");

        try {
            Database.deleteDish(nameOfOwner,dish);
        }catch (SQLException e){
            throw new AppException("删除菜品内容失败！！");
        }

    }

    /**
     * 这个方法用于获取商家的每一类的菜品列表
     */
    public HashMap<String,List<Dish>> showDishes(String nameOfOwner) throws AppException {
        List<Dish> dishes;
        try{
            dishes = Database.getDishList(nameOfOwner);
        }
        catch (SQLException e)
        {
            throw new AppException("获取菜品列表失败！！");
        }


        //一个hashmap，用于记录每个类型的菜品对应的列表
        HashMap<String,List<Dish>> dishHashMap= new HashMap<>();
        List<String> types = new ArrayList<>();
        List<Dish> dishOfType = new ArrayList<>();
        int i,j;
        for(i=0; i<dishes.size(); i++)
        {
            String str = dishes.get(i).getType();
            if (!types.contains(str)) {
                types.add(str);
                for (j=0; j<dishes.size(); j++)
                {
                    if(dishes.get(j).getType().equals(str))
                    {
                        dishOfType.add(dishes.get(j));
                    }
                }
                dishHashMap.put(str,dishOfType);
            }
            dishOfType = new ArrayList<>();
        }
        return dishHashMap;
    }

    /**
     * 这个方法用于传入商家的名称，来获得简介。
     */
    public String obtainIntroduction(String ownerName) throws AppException {
        String introduction;
        try {
            introduction = Database.getOwner(ownerName).getIntroduction();
        }catch (SQLException e)
        {
            throw new AppException("获得简介失败!!");
        }
        return introduction;
    }

    /**
     * 这个方法用于传入商家的名称和新的简介内容，来修改简介。
     */
    public void updateIntroduction(String ownerName, String newIntroduction) throws AppException {

        try {
            this.introduction = newIntroduction;
            Database.changeOwnerIntroduction(ownerName,newIntroduction);
        }catch (SQLException e)
        {
            throw new AppException("修改简介失败!!");
        }
    }

    /**
     * 这个方法用于传入商家的名称，来获得评分。
     */
    public double obtainRating(String ownerName) throws AppException {
        double rating;
        try {
            rating = Database.getOwner(ownerName).getRating();
        }catch (SQLException e)
        {
            throw new AppException("获得简介失败!!");
        }
        return rating;
    }

    /**
     * 这个方法用于传入商家的名称，来获得访问量。
     */
    public int obtainVisit(String ownerName) throws AppException {
        int visit;
        try {
            visit = Database.getOwner(ownerName).getVisit();
        }catch (SQLException e)
        {
            throw new AppException("获得简介失败!!");
        }
        return visit;
    }

    /**
     * 这个方法用于传入商家的名称，来获得菜品列表。
     */
    public List<Dish> obtainDishes(String ownerName) throws AppException {
        // 尝试导出菜品列表。
        List<Dish> tmp_dishes;
        try {
            tmp_dishes = Database.getDishList(ownerName);
        } catch (SQLException e) {
            throw new AppException("导出商家菜品列表失败！！");
        }
        return tmp_dishes;
    }

    /**
     * 这个方法用于传入商家的名称，来获得商家的评论列表。
     */
    public List<Comment> getOwnerComments(String ownerName) throws AppException{
        List<Comment> tmp_comments;
        try {
            tmp_comments = Database.getOwnerComments(ownerName);
        } catch (SQLException e) {
            throw new AppException("导出商家菜品列表失败！！");
        }
        return tmp_comments;
    }
    
    /**
     * 如果商家想要查看自己制作完成的订单，那么我们可以提供商家已完成的订单列表。
     * 注：按照时间排序(最近的在前）
     */
    public List<Order> ShowCooked(){
        return orders_cooked;
    }

    /**
     * 如果商家想要查看自己未制作的订单，那么我们可以提供商家未完成的订单列表。
     * 注：按照时间排序(最近的在前）
     */

    public List<Order> ShowUncooked(){
        return orders_uncooked;
    }

    /**
     * 以下均是getter&setter方法
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
     * 这个方法用于修改商家的名称
     */
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

    /**
     * 这个方法用于修改商家的密码
     */
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


