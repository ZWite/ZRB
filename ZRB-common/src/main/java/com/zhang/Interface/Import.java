package com.zhang.Interface;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: diexi
 * @Date: 2022/4/17 22:30
 * @ClassName: Import
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Import {
    String name() default "";
    String importName() default "";
}
