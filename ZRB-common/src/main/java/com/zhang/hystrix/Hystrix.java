package com.zhang.hystrix;

import com.zhang.StringUtils;
import com.zhang.bean.BeanFactoryU;
import com.zhang.result.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Author: diexi
 * @Date: 2022/4/24 16:49
 * @ClassName: Hystrix
 */
public class Hystrix {

    @Autowired
    private BeanFactoryU beanFactoryU;

    public JsonResult testHsiR(){
        return new JsonResult().ERROR("当前服务已熔断");
    }
}
