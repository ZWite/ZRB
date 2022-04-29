package com.zhang.Exception;

/**
 * 异常捕获
 * @Author: diexi
 * @Date: 2022/4/21 15:50
 * @ClassName: Exception
 */
public class Exception extends Throwable {

    public String runtimeException(String message) {
        return super.getMessage();
    }
}
