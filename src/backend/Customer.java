package backend;

import java.sql.Date;
import java.sql.SQLException;
import java.util.*;

import backend.AppException.AppException;
import backend.Monitor.Constraint;
import backend.CustomerException.Comment.UnQualified;
import backend.CustomerException.Order.AmountIllegal;
import backend.CustomerException.Order.DishUndefined;
import backend.CustomerException.Unfinish.DetailInDetail;
import backend.CustomerException.Unfinish.UnfinishedException;
import database.Database;
import backend.CustomerException.Unfinish.Compare;
import backend.CustomerException.Order.SettleAll;

/**
 * @author JiangXingru
 * 顾客类是系统进行顾客活动的基石，
 * 顾客的行为被定义在顾客类中，顾客的属性也被定义在此。
 */

public class Customer extends User{
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
    private List<Order> orders_finished;

    /**
     * 有已完成的订单，那么这个属性返回的是未完成的订单。
     * 处于安全性考虑，我将变量设为了私有。
     */
    private List<Order> orders_unfinished;

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
     * 这个属性是用户在浏览商家过程中生成的临时订单。
     * 为了保证属性的安全性，我将变量设为了私有。
     */
    private Order tmp_order;

    /**
     * 这个初始化方法可以初始化顾客的基本属性。
     * 这个方法中传入的参数来自登录注册界面中的用户名。
     * 通过用户名向数据库请求订单列表，方法可以自动将
     * 订单分成已完成订单和未完成订单列表。
     * 注：这个方法应该在图形化登录时使用。
     * @param name 用户名
     * @param password 密码
     * @throws AppException 数据库异常
     */
    public Customer(String name, String password) throws AppException {

        // 设置临时订单列表变量用于分类
        List<Order> tmp_orders;

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

        if(!temp_all.containsKey("customer")){
            throw new AppException("用户名属于商家！！");
        }

        // 如果获得密码与现密码不符
        if(!temp_all.get("customer").equals(password)){
            throw new AppException("密码错误！！");
        }

        // 尝试从用户订单中获取电子钱包数额
        try{
            this.wallet = Database.getWallet(name);
        }catch (SQLException e){
            throw new AppException("获取电子钱包数额失败！！");
        }

        // 尝试导出订单列表。
        try{
            tmp_orders = Database.getUserOrderList(name);
        }catch(SQLException e){
            throw new AppException("导出订单列表失败！！");
        }

        // 初始化order_finished和order_unfinished
        this.orders_finished = new ArrayList<>();
        this.orders_unfinished = new ArrayList<>();

        // 将导出的订单列表进行分类
        for(Order order: tmp_orders){
            if(order.completed){
                this.orders_finished.add(order);
            }
            else{
                this.orders_unfinished.add(order);
            }
        }

        // 初始化一些其他变量
        this.owner = null;
        this.dish = null;
        this.tmp_order = null;
    }

    /**
     * 如果顾客想要查看自己订单的话，我们应该返回顾客的订单信息供顾客自己查看。
     * 这个方法应该返回用户的订单列表。
     * 函数的样式我们采用驼峰式编写。
     * 另外，此函数应该不需要传参。
     * 注：这个方法应在图形化查看已完成订单时使用。
     */
    public List<Order> ShowFinished(){
        return this.orders_finished;
    }

    /**
     * 如果顾客想要查看自己订单的话，那么我们也可以提供顾客未完成的订单列表。
     * 另外，此参数不需要传参。
     * 注：这个方法应该在图形化查看未完成订单时使用。
     */

    public List<Order> ShowUnFinished(){
        return this.orders_unfinished;
    }

    /**
     * 如果顾客点击了商家，那么应该对用户正在使用的商家变量进行设置。
     * 同时这一函数也应调用商家的相应函数，向前端返回商家的菜品列表。
     * 注：此方法应在图形化界面中用户点击商家时使用。
     */
    public void SetOwner(Owner arg_owner){
        owner = arg_owner;
    }

    /**
     * 如果用户退出了商家，那么应该将用户正在访问的商家变量清零。
     * 注：此方法应该在图形化界面中用户退出商家时使用。
     */
    public void ResetOwner(){
        owner = null;
    }

    /**
     * 前端在响应到顾客点击了响应的菜品之后，
     * 应该返回对应的菜品变量，顾客讲自己的菜品变量设置为相应。
     * 注：此方法应该在顾客点击菜品之后使用。
     */
    public void SetDish(Dish arg_dish){
        dish = arg_dish;
    }

    /**
     * 这个方法是用户未使用菜品方法，将用户当前菜品属性设置为null。
     */
    public void ResetDish(){
        dish = null;
    }

    /**
     * 如果顾客想要查看自己的姓名的话，此方法可以返回顾客的姓名。
     */
    public String ReturnName(){
        return this.name;
    }

    /**
     * 如果顾客想要查看自己的电子钱包的数额的话，此方法可以返回顾客目前的电子钱包数额。
     */
    public double ReturnWallet(){
        return this.wallet;
    }

    /**
     * 这个方法用于顾客点击一个订单，将顾客当前编辑的订单设置为此订单。
     */
    public void SetMyOrder(Order arg_order){
        this.tmp_order = arg_order;
    }

    /**
     * 这个方法用于用户退出某一些订单的编辑，将当前用户编辑的订单设置为null。
     */
    public void ResetMyOrder(){
        this.tmp_order = null;
    }


    /**
     * 订单自动生成计算方法：如果顾客点击菜品栏旁边的“+”，
     * 在顾客进入此商家第一次点击时应该生成一个新的订单，
     * 往后，只要顾客发生点击事件，
     * 那么应该向临时订单中实时生成菜品。
     * @throws AppException 订单保存异常
     */
    public void AddInOrder() throws AppException {

        // 如果当前订单尚未初始化，那么应该初始化订单
        if(this.tmp_order == null){
            this.tmp_order = new Order();

            // 设置临时订单的顾客名、商家名，初始化价格、顾客完成度和商家完成度的数据。
            this.tmp_order.nameOfCustomer = this.name;
            this.tmp_order.nameOfOwner = this.owner.name;
            this.tmp_order.price = 0;
            this.tmp_order.completed = false;
            this.tmp_order.cooked = false;

            // 暂时先将这一元素设置为null，等待之后提交订单的时候再赋值。
            this.tmp_order.orderTime = null;
        }

        // 遍历菜品哈希表，查看是否有重复？
        HashMap<String, Integer>tmp_dishes = this.tmp_order.dishes;
        String tmp_name = this.dish.name;

        // 如果菜品还没有添加，那么添加菜品
        tmp_dishes.putIfAbsent(tmp_name, 1);

        // 如果添加了菜品，那么将菜品的数量加 1
        if(tmp_dishes.get(tmp_name) != null){
            tmp_dishes.put(tmp_name, tmp_dishes.get(tmp_name) + 1);
        }

        // 实时计算订单的价格
        this.tmp_order.price += dish.price;

        // 实时向数据库中保存当前订单内容
        try {
            Database.insertOrder(tmp_order);
        }catch (SQLException e){
            throw new AppException("向数据库中保存订单内容失败！！");
        }
    }

    /**
     * 未完成订单查询方法：如果用户点击商家开始点餐，
     * 如果用户在此商家尚有未完成订单，那么返回未完成订单对象。
     * 为了更加详细地向前端返回执行的情况，我决定新建类别来更详细地表述信息。
     * 详情请见{@link Compare}
     * {@link UnfinishedException}
     * {@link DetailInDetail}
     * 如果订单都已经完成，那么返回null;
     */
    public DetailInDetail ReturnUnFinished() {

        // 遍历未完成订单表查找是否有未完成订单
        List<Order> tmp_unfinished = this.orders_unfinished;

        for(Order order: tmp_unfinished){

            // 需要捕获异常
            try{
                Compare.compare(order.nameOfOwner, this.owner.name);
            }catch (UnfinishedException exception){

                // 如果在本商家中存在未完成订单，那么将当前浏览的订单设置为这个订单。
                this.tmp_order = order;

                // 直接返回未完成的订单
                DetailInDetail detailInDetail = new DetailInDetail();
                detailInDetail.warning = exception.toString();
                detailInDetail.missing = order;
                return detailInDetail;
            }
        }

        // 如果没有发现未完成订单，那么返回一个空值。
        return null;
    }

    /**
     * 电子钱包充值方法，顾客可以向自己的电子钱包中充值，
     * 充值后电子钱包数据会自动更新。并将更新返回到前端。
     * @param charge_amount 充值的数额
     * @throws AppException 更新电子钱包失败
     */
    public void ChargeWallet(double charge_amount) throws AppException {

        // 更新顾客的电子钱包数额
        this.wallet += charge_amount;

        // 实时向数据库中更新电子钱包数据
        try {
            Database.updateWallet(this.name, this.wallet);
        }catch (SQLException e){
            throw new AppException("更新电子钱包失败！！");
        }
    }

    /**
     * 列表转化方法：如果用户已经完成了未完成订单，那么将
     * 未完成订单推送到已完成顶单列表中，将更改实时保存到数据库。
     * 值得注意的是，列表转换功能应当在顾客点击“订单提交”的时候使用
     * 这里还需要设置一些异常来供前端显示，需要对完成的订单进行一些测试
     * 笔记：对于一个订单来说，异常的数量应该是“有限类”
     */
    public String Submit(){

        // 设置当前商家的菜品列表
        List<Dish> tmp_dishes;

        // 爬取当前商家的订单列表
        tmp_dishes = owner.dishes;

        // 测试错误情况
        // 我的笔记：
        // 错误情况无非是两种：1、菜品不存在；2、菜品数额不足。
        for(String name: tmp_order.dishes.keySet()){
            try{
                SettleAll.TestExist(tmp_dishes, name);
                SettleAll.TestAmount(tmp_dishes, name, tmp_order.dishes.get(name));
            }catch (DishUndefined e){
                return e.toString();
            }catch (AmountIllegal e){
                return e.toString();
            }
        }

        // 如果测试都成功

        // 首先设置arg_order中的一系列数据

        // 将此订单设置称为已完成
        tmp_order.completed = true;

        // 将订单完成日期设置为当前日期
        tmp_order.orderTime = new Date(System.currentTimeMillis());

        // 将此订单加入已完成订单列表中
        this.orders_finished.add(tmp_order);

        // 从未完成订单列表中删除此订单
        this.orders_unfinished.remove(tmp_order);

        // 向数据库中实时保存订单,并抓取可能发生的异常
        try{
            Database.insertOrder(tmp_order);
        }catch (SQLException e){
            return "数据库插入发生错误！！";
        }

        //如果一切正常

        // 应该将用户当前的订单设置为空
        tmp_order = null;

        return "您的订单已经提交成功！！";
    }

    /**
     * 退出订单方法：如果用户在途中退出或是点击了返回按键，应当应用此方法。
     * @throws AppException 数据库异常
     */
    public void QuitOrder() throws AppException {

        // 向未完成订单列表中保存tmp_order
        this.orders_unfinished.add(tmp_order);

        // 向数据库中更新订单列表
        try{
            Database.insertOrder(tmp_order);
        }catch (SQLException e){
            throw new AppException("退出订单时向数据库保存失败！！");
        }
    }

    /**
     * 用户评分方法，前端传入商家的评分，
     * 实时将“用户 商家 评分 评分内容”四元组传入数据库。
     * 如果数据库中已有记录，那么保存相应的更改。
     * 如果用户在当前商家里没有订单，那么抛出一个评分异常。
     * 也可以输入评分内容。
     * @param rating 前端对商家的评分
     * @param comment 前端对商家的评分内容
     * @throws AppException 数据库插入评论异常
     * @throws backend.CustomerException.Comment.UnQualified 用户没有资格评价异常
     */
    public void Comment(double rating, String comment) throws UnQualified, AppException{

        // 查看用户已完成订单中是否有当前商家的订单，这里需要遍历订单列表
        int i;
        for(i = 0; i < orders_finished.size(); i++){
            Order temp = orders_finished.get(i);

            // 如果用户在这家店中用过餐，终止for循环
            if(temp.nameOfOwner.equals(owner.name)){
                break;
            }
        }

        // 如果没有，那么抛出无资格评价异常
        if(i == orders_finished.size()){
            throw new UnQualified();
        }

        // 如果用户有资格评论，将评论插入数据库
        try{
        Database.updateOwnerRating(this.name, this.owner.name, rating, comment);
        }catch (SQLException e){
            throw new AppException("向数据库中插入评论失败！！");
        }
    }

    /**
     * 注意：我的设想是用户点击订单之后才可能会有删除订单动作
     * 删除订单方法：顾客可以删除订单，
     * 远程数据库实时保存信息。
     * @throws AppException 删除订单异常
     */
    public void DeleteOrder() throws AppException{

        // 如果已完成订单列表中存在元素
        orders_finished.remove(tmp_order);

        // 如果未完成订单列表中存在元素
        orders_unfinished.remove(tmp_order);

        // 从数据库中删除对应元素
        try{
            Database.deleteOrder(tmp_order);
        }catch (SQLException e){
            throw new AppException("删除订单失败！！");
        }
    }

    /**
     * 这个是User类中修改用户名的实现
     * @param newName 要修改成的新用户名
     * @throws AppException 通用异常
     */
    @Override
    public void modifyName(String newName) throws AppException {

        // 检验用户名格式
        Constraint.CheckUname(newName);

        // 将用户名插入数据库
        try{
            Database.changeCustomerName(this.name, newName);
        }catch (SQLException e){
            throw new AppException("数据库异常！！");
        }

        // 更改用户的用户名
        this.name = newName;
    }

    /**
     * 这个是User类中修改密码的实现
     * @param newPwd 要修改成的新密码
     * @throws AppException 通用异常
     */
    @Override
    public void modifyPwd(String newPwd) throws AppException {

        // 检验密码格式
        Constraint.CheckPwd(newPwd);

        // 将新密码写入数据库
        try{
            Database.changeCustomerPassword(this.name, newPwd);
        }catch (SQLException e){
            throw new AppException("数据库异常！！");
        }
    }
}
