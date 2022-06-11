package backend;

import javax.swing.*;
import java.util.ArrayList;

/**
 * @author JiangXingru
 * 顾客类是系统进行顾客活动的基石，
 * 顾客的行为被定义在顾客类中，顾客的属性也被定义在此。
 */

public class Customer {

    /**
     * 这是商家姓名定义的属性变量，出于系统安全考虑，我打算将此变量设为私有。
    */
    private char[] name;

    /**
     * 这是顾客的电子钱包属性，通过这个属性，我们可以知道顾客的余额。
     * 处于安全考虑，我将属性设置称为private级别。
     */
    private double wallet;

    /**
     * 这是顾客所定的订单种类的集合，处于系统安全考虑，我打算将此变量设为私有，
     * 通过初始化订单，可以获取到数据。
     * 特别注意的是，这个订单应该是顾客已完成的订单列表。我们采取横线式进行处理。
     */
    private ArrayList<Customer> orders_finished;

    /**
     * 有已完成的订单，那么这个属性返回的是未完成的订单。
     * 处于安全性考虑，我将变量设为了私有。
     */
    private ArrayList<Customer> orders_unfinished;

    /**
     * 这个属性是用户专属商家的变量，通过这一变量的使用，用户可以确定自己正在浏览哪一家餐厅。
     * 为了确保属性的安全性，我将变量设置为了私有。
     */
    private Owner owner;

    /**
     * 这个属性是用户记录自己在相应商家的哪一个相应菜品选择框中。
     * 为了保证属性的安全性，我将变量设为了私有。
     */
    private Dish dish;

    /**
     * 这个初始化方法可以初始化顾客的基本属性。
     * 这个方法中传入的参数来自登录注册界面中的用户名。
     * 通过用户名向数据库请求订单列表，方法可以自动将
     * 订单分成已完成订单和未完成订单列表。
     * 注：这个方法应该在图形化登录时使用。
     */
    public Customer(char[] name){

        /*
          在这里将顾客的用户名设为登录注册页面传入的用户名。
         */
        this.name = name;

        /*
        在这里通过用户名向数据库请求订单列表。
         */

        /*
        通过分类内容筛选出已完成订单和未完成订单列表。
         */
    }

    /**
     * 如果顾客想要查看自己订单的话，我们应该返回顾客的订单信息供顾客自己查看。
     * 这个方法应该返回用户的订单列表。
     * 函数的样式我们采用驼峰式编写。
     * 另外，此函数应该不需要传参。
     * 注：这个方法应在图形化查看已完成订单时使用。
     */
    public ArrayList ShowFinished(){
        return this.orders_finished;
    }

    /**
     * 如果顾客想要查看自己订单的话，那么我们也可以提供顾客未完成的订单列表。
     * 另外，此参数不需要传参。
     * 注：这个方法应该在图形化查看未完成订单时使用。
     */

    public ArrayList ShowUnFinished(){
        return this.orders_unfinished;
    }

    /**
     * 如果顾客点击了商家，那么应该对用户正在使用的商家变量进行设置。
     * 同时这一函数也应调用商家的相应函数，向前端返回商家的菜品列表。
     * 注：此方法应在图形化界面中用户点击商家时使用。
     */
    public void SetOwner(Owner arg_owner){
        this.owner = arg_owner;
    }

    /**
     * 如果用户退出了商家，那么应该将用户正在访问的商家变量清零。
     * 注：此方法应该在图形化界面中用户退出商家时使用。
     */
    public void ResetOwner(){
        this.owner = null;
    }

    /**
     * 前端在响应到顾客点击了响应的菜品之后，
     * 应该返回对应的菜品变量，顾客讲自己的菜品变量设置为相应。
     * 注：此方法应该在顾客点击菜品之后使用。
     */
    public void SetDish(Dish arg_dish){
        this.dish = arg_dish;
    }

    /**
     * 这个方法是用户未使用菜品方法，将用户当前菜品属性设置为null。
     */
    public void ResetDish(){
        this.dish = null;
    }

    /**
     * 订单自动生成计算方法：如果顾客点击菜品栏旁边的“+”，
     * 在顾客进入此商家第一次点击时应该生成一个新的订单，
     * 往后，只要顾客发生点击事件，
     * 那么应该向临时订单中实时生成菜品。
     */
    public void CreateOrder(){

    }

    /**
     * 未完成订单查询方法：如果用户点击商家开始点餐，
     * 如果用户在此商家尚有未完成订单，那么返回未完成订单对象。
     */
    public void ReturnUnFinished(){

    }

    /**
     * 电子钱包充值方法，顾客可以向自己的电子钱包中充值，
     * 充值后电子钱包数据会自动更新。并将更新返回到前端。
     */
    public double ChargeWallet(int charge_amount){
        return 0;
    }

    /**
     * 列表转化方法：如果用户已经完成了未完成订单，那么将
     * 未完成订单推送到已完成顶单列表中，将更改实时保存到数据库。
     */
    public void Swift(){

    }

    /**
     * 用户评分方法，前端传入商家的评分，
     * 实时将“用户 商家 评分 评分内容”四元组传入数据库。
     * 如果数据库中已有记录，那么保存相应的更改。
     * 如果用户在当前商家里没有订单，那么抛出一个评分异常。
     * 也可以输入评分内容。
     */
    public void Comment(){

    }

    /**
     * 删除订单方法：顾客可以删除订单，
     * 远程数据库实时保存信息。
     */
    public void DeleteOrder(){

    }
}
