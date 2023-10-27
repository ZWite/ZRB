package com.zhang.UserUtils;

import com.zhang.assertUtils.AssertUtils;
import com.zhang.jwtToken.JwtToken;
import com.zhang.pojo.User;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @description 用户基本工具类
 * @author zhangmengxin
 * @date 2023/1/18 14:07
 */
public class UUtils {

    private static String jmyz = "wzy980622";

    /**
     * @description  用户注册密码加密
     * @method generatePwdEncryption
     * @author zhangmengxin
     * @date 2023/1/18 14:09
     * @param user 用户对象
     * @return String 加密结果
     */
    public static String generatePwdEncryption(User user){
        AssertUtils.isNull(user == null,"当前用户不能为null");
        return JwtToken.createJToken(user.getUserName());
    }

    /**
     * @description  生成用户唯一标识
     * @method generateOnlyUserSignleEncryption
     * @author zhangmengxin
     * @date 2023/1/18 14:54
     * @param user  用户对象
     * @return  String 加密结果
     */
    public static String generateOnlyUserSignleEncryption(User user){
        AssertUtils.isNull(user == null,"当前用户不能为null");
        return user.getUserName()+md5(user.getUserName());
    }


    /**
     * @description 简单加密
     * @method md5
     * @author zhangmengxin
     * @date 2023/1/18 15:01
     * @param null
     * @return
     */
    public static String md5(String text) {
        try {
            MessageDigest md =  MessageDigest.getInstance("md5");
            byte[] result = md.digest(text.getBytes());//result长度固定为16
            StringBuffer sb = new StringBuffer();

            // Byte型数据可以表示2^8=256种数值，无符号二位十六进制数也恰好可以表示16^2=256种数值
            // 这里将每个byte型数据变换成一个长度固定为2的字符串，字符串内容为一个无符号二位十六进制数
            for (byte b : result){
                int number = b & 0xff;
                //java中整数默认的数据类型是int，计算 b&0xff 时，0xff默认为int型，故先将b转型为int，再做4个字节上的位与运算
                //上一行代码的作用在于，将[-128,127]上的byte型数据一对一映射为[0,255]上的int型数据，以便导入下面的函数
                String hex = Integer.toHexString(number);
                if (hex.length() == 1){
                    hex = "0"+hex;
                }
                sb.append(hex);
            }

            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static void main(String[] args) {
//        System.out.println(generateOnlyUserSignleEncryption("admin"));
    }
}
