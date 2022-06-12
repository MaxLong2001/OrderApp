package backend.AppException;

/**
 * @version 1.0
 * @author JiangXingru
 * Ӧ�鳤Ҫ�����������������Ӳ�����쳣ת�������ǱȽ�ͨ���׶����쳣��
 */

public class AppException extends Exception{

    /**
     * �������Ҫһ��ͨ���׶��ı�ʶ����
     * ��Ҫ��һ��˽����������ʾ
     */
    private String warning;

    /**
     * �ڹ��췽������������Ҫ���ַ��������ַ������浽˽��������
     * @param arg_warning �û��Զ���ľ������
     */
    public AppException(String arg_warning){
        warning = arg_warning;
    }

    /**
     * ������������������Ҫ���쳣����
     */
    @Override
    public String toString(){
        return this.warning;
    }
}
