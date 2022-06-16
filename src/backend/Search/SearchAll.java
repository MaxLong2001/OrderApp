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
 * �������ר�����ڼ��������������
 */

public class SearchAll {

    /**
     * �����������������Ʒ�б��ȡ��ؼ�����ƥ��Ĳ�Ʒ
     * @param key_word �˿�����Ĺؼ���
     * @param dishes ��ȡ���Ĳ�Ʒ�б�
     */
    public static boolean FindDish(String key_word, List<Dish> dishes){

        // ���ؼ���ת��Ϊ������ʽ��ƥ����
        String regex = key_word + "+";

        // ������Ʒ�б���Ҷ�Ӧ��Ʒ
        for(Dish dish: dishes){

            // �����Ʒ�к��йؼ���
            if(dish.getName().matches(regex)){

                // ���أ��ҵ���
                return true;
            }
        }

        // ���û������ƥ���Ʒ�����ش���
        return false;
    }

    /**
     * ����������̼����л�ȡ���йؼ��ֵ��̼�����
     * ͬʱ�����̼ҵĲ�Ƥ�б�����к͹ؼ���ƥ��Ĳ�Ʒ�������̼�Ҳ�����б�
     * @param owners ����ȫ���̼��б�
     * @param key_word �˿�����Ĺؼ���
     * @throws AppException ͨ���쳣
     */
    public static List<Owner> MySearch(String key_word, List<Owner> owners) throws AppException {

        // ��������ؼ���Ϊ�գ���ô�׳�ͨ���쳣��ֱ�ӷ���
        if(key_word.equals("")){
            throw new AppException("�����ؼ���Ϊ�գ���");
        }

        // ���ؼ�����ʽת����Ϊ������ʽ�еĴ�ƥ����ʽ
        String regex = key_word + "+";

        // �����̼��б�
        List<Owner> result = new ArrayList<>();

        // ��ʼ�����ַ���Ҫ����̼��б�
        List<Owner> hasName = new ArrayList<>();

        // ��ʼ����Ʒ������Ҫ����̼��б�
        List<Owner> hasDish = new ArrayList<>();

        // �����̼ұ�õ����йؼ��ֵ��̼�
        for(Owner owner: owners){

            // ����̼���ƥ��ؼ�������һ��
            if(owner.getName().matches(regex)){
                hasName.add(owner);
            } else{

                // ������ʱ��Ʒ�б����
                List<Dish> dishes;

                // ���Ի�ȡ�̼ҵĲ�Ʒ�б�
                try{
                    dishes = Database.getDishList(owner.getName());
                }catch (SQLException e){
                    throw new AppException("���ݿ���󣡣���ȡ��Ʒ�б��쳣");
                }

                // ����Ʒ�б����Ƿ��йؼ��֣�������У����뵽�б���
                if(SearchAll.FindDish(key_word, dishes)){
                    hasDish.add(owner);
                }
            }
        }

        // ����Ĭ���Ƽ���ʽ�������б�����
        ForCustomer.OwnerRecommend(hasName);
        ForCustomer.OwnerRecommend(hasDish);

        // ���������б�ϲ���Ϊ���ս��
        result.addAll(hasName);
        result.addAll(hasDish);

        // �������ս��
        return result;
    }
}
