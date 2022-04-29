package com.zhang.Exception;

import com.zhang.result.JsonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 异常统一处理
 * @Author: diexi
 * @Date: 2022/4/24 9:19
 * @ClassName: ExceptionCommonDispose
 */
//捕获全局异常
@RestControllerAdvice
public class ExceptionCommonDispose {

    //全局异常处理
    @ExceptionHandler(Exception.class)
    public JsonResult DisPose(){
        return new JsonResult().ERROR();
    }
}
