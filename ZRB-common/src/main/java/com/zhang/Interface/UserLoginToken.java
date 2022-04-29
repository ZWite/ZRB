package com.zhang.Interface;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: diexi
 * @Date: 2022/3/26 23:06
 * @ClassName: UserLoginToken  需要登录才能进行操作的注解UserLoginToken
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface UserLoginToken {
    //是否需要校验
    boolean required() default true;

    //这里还需要一个身份认证
}