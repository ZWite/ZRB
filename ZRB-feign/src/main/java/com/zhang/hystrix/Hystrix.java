package com.zhang.hystrix;

import com.zhang.bean.BeanFactoryU;
import com.zhang.pojo.User;
import com.zhang.result.JsonResult;
import com.zhang.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Author: diexi
 * @Date: 2022/4/24 16:49
 * @ClassName: Hystrix
 */
@Component
public class Hystrix implements LoginService {

    @Autowired
    private BeanFactoryU beanFactoryU;

    public JsonResult testHsiR() {
        return new JsonResult().ERROR("当前服务已熔断");
    }

    @Override
    public void login(@RequestBody User user) {
        System.out.println("1111");
        return;
    }
}
