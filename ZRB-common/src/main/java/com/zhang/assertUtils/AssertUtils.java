package com.zhang.assertUtils;

import org.springframework.lang.Nullable;

import java.util.function.Supplier;

/**
 * @Author: diexi
 * @Date: 2022/4/24 16:30
 * @ClassName: AssertUtils
 */
public class AssertUtils {
    public AssertUtils(){
    }

    public static void isNull(Boolean exception,String message){
        if (exception){
            throw  new NullPointerException(message);
        }
    }

    public static void notNull(Boolean exception,String message){
        if (exception){
            throw  new NullPointerException(message);
        }
    }

    public static void notTrue(Boolean exception,Supplier<String> message){
        if (exception){
            throw  new IllegalArgumentException(nullSafeGet(message));
        }
    }

    @Nullable
    private static String nullSafeGet(@Nullable Supplier<String> messageSupplier) {
        return messageSupplier != null ? (String)messageSupplier.get() : null;
    }
}
