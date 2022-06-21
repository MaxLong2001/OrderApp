package backend.Recommend;

import backend.AppException.AppException;
import backend.Owner;
import database.Database;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0
 * @author JiangXingru
 * 这是一个用于排序的临时类
 */

class ForSort{
    Owner owner;
    double rank;
}

/**
 * 这个类是顾客登录进系统的默认推荐算法。
 */

public class ForCustomer{

    /**
     * 这个变量用于调整推荐算法的权重
     */
    private static int define = 50;

    /**
     * 这个方法用来设置权重
     * @param arg_define 参数权重
     */
    public static void setDefine(int arg_define){
        define = arg_define;
    }

    /**
     * 这里是默认的推荐算法
     * @param owners 商家列表
     * @throws backend.AppException.AppException 通用异常
     */
    public static List<Owner> OwnerRecommend(List<Owner> owners) throws AppException {
        // 推荐全数列表
        List<ForSort> forSorts = new ArrayList<>();
        // 遍历商家表获取权重表
        for (Owner owner : owners) {
            double temp_rating;
            double rank;
            // 获取每个商家的评分
            try {
                temp_rating = Database.getOwnerRating(owner.getName());
            } catch (SQLException e) {
                throw new AppException("数据库异常！！");
            }
            // 计算权重
            rank = temp_rating * define + owner.visit * (100 - define);
            // 建立并插入“全数”
            ForSort forSort = new ForSort();
            forSort.owner = owner;
            forSort.rank = rank;
            forSorts.add(forSort);
        }
        // 对"全数列表”进行从大到小排序
        forSorts.sort(((o1, o2) -> (int) (o2.rank - o1.rank)));
        // 清空以前的商家表
        owners.clear();
        // 将“全数”表，赋给当前商家表
        for (ForSort forSort : forSorts) {
            // 临时ForSort变量
            owners.add(forSort.owner);
        }
        return owners;
    }
}
