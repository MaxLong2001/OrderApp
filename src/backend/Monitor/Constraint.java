package backend.Monitor;

import backend.AppException.AppException;

/**
 * @version 1.0
 * @author JiangXingru
 * 这个类为了检验用户名和密码存在
 */
public class Constraint {

    /**
     * 这个类用来检验用户名
     * @param arg_name 输入的名称参数;
     * @throws AppException 用户名不合规异常
     * 1、用户名规则，大于等于4个字符小于等于10个字符;
     * 2、中间没有空格;
     */
    public static void CheckUname(String arg_name) throws AppException {

        // 这个数组用来转化String
        char[] temp_name = arg_name.toCharArray();

        // 如果字符数不符合规则
        if(temp_name.length < 3){
            throw new AppException("用户名不能小于3个字符！！");
        }
        if(temp_name.length > 10){
            throw new AppException("用户名不能大于10个字符！！");
        }

        // 检验用户名中间是否有空格
        if(arg_name.contains(" ")){
            throw new AppException("用户名不能含有空格！！");
        }
    }

    /**
     * 这个函数用来检验密码
     * @param arg_password 密码参数
     * @throws AppException 密码不正确异常
     * 1、密码规则：密码中包含字符；
     * 2、密码规则：密码中包含数字；
     * 3、密码规则：密码中包含下划线
     */
    public static void CheckPwd(String arg_password) throws AppException{

        // 密码中应该包含英文字母
        String regex1 = ".*[a-zA-z].*";
        boolean result1 = arg_password.matches(regex1);
        if(!result1){
            throw new AppException("密码中应包含英文字母！！");
        }

        // 密码中应该包含数字
        String regex2 = ".*[0-9].*";
        boolean result2 = arg_password.matches(regex2);
        if(!result2){
            throw new AppException("密码中应包含数字！！");
        }

        // 密码中应包含下划线
        if(!arg_password.contains("_")){
            throw new AppException("密码中应包含下划线！！");
        }
    }
}
