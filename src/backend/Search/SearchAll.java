package backend.Search;

import backend.AppException.AppException;
import backend.Dish;
import backend.Owner;
import backend.Recommend.ForCustomer;
import database.Database;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0
 * @author JiangXingru
 * 这个类是专门用于计算搜索结果的类
 */

public class SearchAll {

    /**
     * 这个方法用来遍历菜品列表获取与关键字相匹配的菜品
     * @param key_word 顾客输入的关键字
     * @param dishes 获取到的菜品列表
     */
    public static boolean FindDish(String key_word, List<Dish> dishes){

        // 将关键字转化为正则表达式待匹配项

        // 遍历菜品列表查找对应菜品
        for(Dish dish: dishes){

            // 如果菜品中含有关键字
            if(dish.getName().contains(key_word)){

                // 返回：找到了
                return true;
            }
        }

        // 如果没有正则匹配菜品，返回错误
        return false;
    }

    /**
     * 这个方法从商家名中获取含有关键字的商家名，
     * 同时遍历商家的菜皮列表，如果有和关键字匹配的菜品名，则将商家也加入列表
     * @param owners 输入全部商家列表
     * @param key_word 顾客输入的关键字
     * @throws AppException 通用异常
     */
    public static List<Owner> MySearch(String key_word, List<Owner> owners) throws AppException {

        // 首先如果关键字为空，那么抛出通用异常并直接返回
        if(key_word.equals("")){
            throw new AppException("搜索关键字为空！！");
        }

        // 将关键字正式转化成为正则表达式中的待匹配表达式

        // 最终商家列表
        List<Owner> result = new ArrayList<>();

        // 初始化名字符合要求的商家列表
        List<Owner> hasName = new ArrayList<>();

        // 初始化菜品名符合要求的商家列表
        List<Owner> hasDish = new ArrayList<>();

        // 遍历商家表得到含有关键字的商家
        for(Owner owner: owners){

            // 如果商家名匹配关键字至少一次
            if(owner.getName().contains(key_word)){
                hasName.add(owner);
            } else{

                // 建立临时菜品列表变量
                List<Dish> dishes;

                // 尝试获取商家的菜品列表
                try{
                    dishes = Database.getDishList(owner.getName());
                }catch (SQLException e){
                    throw new AppException("数据库错误！！获取菜品列表异常");
                }

                // 检查菜品列表中是否含有关键字？如果含有，加入到列表当中
                if(SearchAll.FindDish(key_word, dishes)){
                    hasDish.add(owner);
                }
            }
        }

        // 按照默认推荐方式将两个列表排序
        ForCustomer.OwnerRecommend(hasName);
        ForCustomer.OwnerRecommend(hasDish);

        // 将两部分列表合并成为最终结果
        result.addAll(hasName);
        result.addAll(hasDish);

        // 返回最终结果
        return result;
    }
}
