package backend.CustomerException.Order;

/**
 * �������Ϊ���ж϶����ύʱ���쳣�������ġ�
 * ������ֶ����й˿͵����ֲ����ڣ����׳����쳣��
 */

public class UserUndefined extends Exception{

    // �����ӡ�쳣����
    @Override
    public String toString(){
        return "�û�δ���壡����";
    }
}
