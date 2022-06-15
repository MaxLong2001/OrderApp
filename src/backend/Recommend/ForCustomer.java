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
 * ����һ�������������ʱ��
 */

class ForSort{
    Owner owner;
    double rank;
}

/**
 * ������ǹ˿͵�¼��ϵͳ��Ĭ���Ƽ��㷨��
 */

public class ForCustomer{

    /**
     * ����������ڵ����Ƽ��㷨��Ȩ��
     */
    private static int define = 50;

    /**
     * ���������������Ȩ��
     * @param arg_define ����Ȩ��
     */
    public static void setDefine(int arg_define){
        define = arg_define;
    }

    /**
     * ������Ĭ�ϵ��Ƽ��㷨
     * @param owners �̼��б�
     * @throws backend.AppException.AppException ͨ���쳣
     */
    public static List<Owner> OwnerRecommend(List<Owner> owners) throws AppException {

        // �Ƽ�ȫ���б�
        List<ForSort> forSorts = new ArrayList<>();

        // �����̼ұ��ȡȨ�ر�
        for (Owner owner : owners) {

            // ��ʱ�̼ұ���
            // ��ʱ���ֱ���
            double temp_rating;

            // ��ʱȨ�ر���
            double rank;

            // ��ȡÿ���̼ҵ�����
            try {
                temp_rating = Database.getOwnerRating(owner.getName());
            } catch (SQLException e) {
                throw new AppException("���ݿ��쳣����");
            }

            // ����Ȩ��
            rank = temp_rating * define + owner.visit * (100 - define);

            // ���������롰ȫ����
            ForSort forSort = new ForSort();
            forSort.owner = owner;
            forSort.rank = rank;
            forSorts.add(forSort);
        }

        // ��"ȫ���б����дӴ�С����
        forSorts.sort(((o1, o2) -> (int) (o2.rank - o1.rank)));

        // �����ǰ���̼ұ�
        owners.clear();

        // ����ȫ������������ǰ�̼ұ�
        for (ForSort forSort : forSorts) {
            // ��ʱForSort����
            owners.add(forSort.owner);
        }

        return owners;
    }
}
