package com.zhang.result.Enum;

/**
 * @Author: diexi
 * @Date: 2022/4/22 22:23
 * @ClassName: ResultEnum
 */
public class ResultEnum {

    public static final class ResultCode{
        //ok
        public static final String RESULT_SUCCESS = "200";
        //Created 已创建。成功请求并创建了新的资源
        public static final String RESULT_CREATED = "201";
        //	Accepted  已接受。已经接受请求，但未处理完成
        public static final String RESULT_ACCEPTED = "202";
        //	Non-Authoritative Information 非授权信息。请求成功。但返回的meta信息不在原始的服务器，而是一个副本
        public static final String RESULT_NAI = "203";
        public static final String RESULT_ERROR = "500";
        //Bad Request
        public static final String RESULT_BAD_REQUEST = "400";
        public static final String RESULT_NOT_F = "404";
        public static final String RESULT_WRONG = "401";


        private ResultCode(){
        }

    }
}
