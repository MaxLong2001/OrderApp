package backend;

import backend.AppException.AppException;

/**
 * @version 1.0
 * @author JiangXingru
 * �����Ϊ�˼����û������������
 */
public class CheckLogin {

    /**
     * ��������������û���
     * @param arg_name ��������Ʋ���;
     * @throws AppException �û������Ϲ��쳣
     * 1���û������򣬴��ڵ���4���ַ�С�ڵ���10���ַ�;
     * 2���м�û�пո�;
     */
    public static void CheckUname(String arg_name) throws AppException {

        // �����������ת��String
        char[] temp_name = arg_name.toCharArray();

        // ����ַ��������Ϲ���
        if(temp_name.length < 4){
            throw new AppException("�û�������С��4���ַ�����");
        }
        if(temp_name.length > 10){
            throw new AppException("�û������ܴ���10���ַ�����");
        }

        // �����û����м��Ƿ��пո�
        if(arg_name.contains(" ")){
            throw new AppException("�û������ܳ���10���ַ�����");
        }
    }

    /**
     * �������������������
     * @param arg_password �������
     * @throws AppException ���벻��ȷ�쳣
     * 1��������������а����ַ���
     * 2��������������а������֣�
     * 3��������������а����»���
     */
    public static void CheckPwd(String arg_password) throws AppException{

        // ������Ӧ�ð���Ӣ����ĸ
        String regex1 = ".*[a-zA-z].*";
        boolean result1 = arg_password.matches(regex1);
        if(!result1){
            throw new AppException("������Ӧ����Ӣ����ĸ����");
        }

        // ������Ӧ�ð�������
        String regex2 = ".*[0-9].*";
        boolean result2 = arg_password.matches(regex2);
        if(!result2){
            throw new AppException("������Ӧ�������֣���");
        }

        // ������Ӧ�����»���
        if(!arg_password.contains("_")){
            throw new AppException("������Ӧ�����»��ߣ���");
        }
    }
}
