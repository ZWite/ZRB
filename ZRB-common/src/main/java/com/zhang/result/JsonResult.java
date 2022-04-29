package com.zhang.result;

import com.zhang.result.Enum.ResultEnum;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 封装json对象
 *
 * @Author: diexi
 * @Date: 2022/4/22 19:46
 * @ClassName: JsonResult
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true) // 链式写法
public class JsonResult {
    public boolean success;
    private String code;
    private String message;
    private Object object;

    public JsonResult SUCCESS(String message, Object object) {
        return new JsonResult().setSuccess(true)
                .setCode(ResultEnum.ResultCode.RESULT_SUCCESS)
                .setMessage(message)
                .setObject(object);
    }

    public JsonResult SUCCESS(Object object) {
        return new JsonResult().setSuccess(true)
                .setCode(ResultEnum.ResultCode.RESULT_SUCCESS)
                .setMessage("成功")
                .setObject(object);
    }

    public JsonResult SUCCESS(String message) {
        return new JsonResult().setSuccess(true)
                .setCode(ResultEnum.ResultCode.RESULT_SUCCESS)
                .setMessage(message);
    }

    public JsonResult SUCCESS() {
        return new JsonResult().setSuccess(true)
                .setCode(ResultEnum.ResultCode.RESULT_SUCCESS)
                .setMessage("成功");
    }

    public JsonResult ERROR(Object object) {
        return new JsonResult().setSuccess(true)
                .setCode(ResultEnum.ResultCode.RESULT_BAD_REQUEST)
                .setMessage("失败")
                .setObject(object);
    }

    public JsonResult ERROR(String message,Object object) {
        return new JsonResult().setSuccess(true)
                .setCode(ResultEnum.ResultCode.RESULT_BAD_REQUEST)
                .setMessage(message)
                .setObject(object);
    }

    public JsonResult ERROR(String message) {
        return new JsonResult().setSuccess(true)
                .setCode(ResultEnum.ResultCode.RESULT_BAD_REQUEST)
                .setMessage(message);
    }


    public JsonResult ERROR() {
        return new JsonResult().setSuccess(true)
                .setCode(ResultEnum.ResultCode.RESULT_BAD_REQUEST)
                .setMessage("失败");
    }
}
