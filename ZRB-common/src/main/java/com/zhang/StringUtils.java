package com.zhang;

import com.zhang.assertUtils.AssertUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: diexi
 * @Date: 2022/4/24 16:17
 * @ClassName: StringUtils
 */
public class StringUtils {

    /**
     * 数据表字段名转换为驼峰式名字的实体类属性名
     *
     * @param str 数据表字段名
     * @return 转换后的驼峰式命名
     */
    private static Pattern linePattern = Pattern.compile("_(\\w)");

    public static String camelize(String str) {
//            str = str.toLowerCase();
        Matcher matcher = linePattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    public static String toLowerCaseFirst(String str) {
        AssertUtils.notNull(StringUtils.isEmpty(str),"无法变更空字符串");
        StringBuffer sb = new StringBuffer();
        sb.append(str.substring(0, 1).toLowerCase()).append(str.substring(1));
        return sb.toString();
    }


    public static Boolean isNotEmpty(Object var){
        if(var instanceof String){
            return var != null && !"".equalsIgnoreCase(var.toString().trim());
        }
        return false;
    }

    public static Boolean isEmpty(Object var){
        if(var instanceof String){
            return var == null && "".equalsIgnoreCase(var.toString().trim());
        }
        return false;
    }
}
