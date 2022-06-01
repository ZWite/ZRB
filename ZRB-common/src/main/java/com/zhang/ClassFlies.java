package com.zhang;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Iterator;

/**
 * @author: diexi
 * @description: 反射工具类
 * @date: 2022/6/1 15:39
 * @version 1.0
 */
@Slf4j
public class ClassFlies {

    public <T> void getValue(Collection<T> collection){
        //遍历集合数据，产生数据行
        Iterator<T> it = collection.iterator();
        while(it.hasNext()){
            T t = it.next();
            Class<?> aClass = t.getClass();
            Field[] field = aClass.getDeclaredFields();
            for (Field field1 : field) {
                String name = field1.getName();
                char[] chars = name.toCharArray();
                chars[0] = upperCase(chars[0]);
                String s = String.valueOf(chars);
                StringBuilder sb = new StringBuilder();
                sb.append("get").append(s);
                String s1 = String.valueOf(sb);
                Object invoke = null;
                try {
                    Method method = aClass.getMethod(s1);
                    invoke = method.invoke(t,new Object[]{});
                } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                    log.error("ClassFlies.getValue方法出现异常"+ e);
                    //e.printStackTrace();
                }
                //其它数据类型都当作字符串简单处理
                if (invoke != null) {
                    invoke = invoke.toString();
                } else {
                    invoke = null;
                }
            }
        }
    }

    /**
     * 提供一个反射工具，获取字段值，利用二进制运算进行首字母大写
     * @param c
     * @return 异或之后赋值的结果
     */
    public char upperCase(char c){
        if(97<=c && c<=122){
            c^=32;
        }
        return c;
    }


    /**
     * 单个对象的某个键的值
     * @param obj 对象
     * @param key 键
     * @return Object 键在对象中所对应得值 没有查到时返回空字符串
     */
    public static Object getValueByKey(Object obj, String key) {
        // 得到类对象
        Class<?> cls = obj.getClass();
        /* 得到类中的所有属性集合 */
        Field[] fs = cls.getDeclaredFields();
        for (int i = 0; i < fs.length; i++) {
            Field f = fs[i];
            //设置些属性是可以访问的
            f.setAccessible(true);
            try {
                if (f.getName().endsWith(key)) {
                    System.out.println("单个对象的某个键的值==反射==" + f.get(obj));
                    return f.get(obj);
                }
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return "";
    }
}
