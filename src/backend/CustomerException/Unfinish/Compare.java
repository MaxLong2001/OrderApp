package backend.CustomerException.Unfinish;

/**
 * @version 1.0
 * @author JiangXingru
 * ����һ���ж϶������̼ҵ������Ƿ�������̼�������ͬ����
 * �����ͬ���׳�һ������δ����쳣
 */

public class Compare {

    /**
     * ������������ж����������Ƿ���ͬ
     * @param a ����a
     * @param b ����b
     * @throws UnfinishedException δ�����쳣
     */
    public static void compare(String a, String b) throws UnfinishedException {
        if(a.equals(b)){
            throw new UnfinishedException();
        }
    }
}
