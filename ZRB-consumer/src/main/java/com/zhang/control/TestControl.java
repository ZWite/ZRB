package com.zhang.control;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.zhang.StringUtils;
import com.zhang.bean.BeanFactoryU;
import com.zhang.result.JsonResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: diexi
 * @Date: 2022/4/24 11:51
 * @ClassName: TestControl
 */
@RestController
@RequestMapping("test")
public class TestControl {

    @Autowired
    private BeanFactoryU beanFactoryU;

    @GetMapping("/mid")
    @HystrixCommand(defaultFallback = "testHsiR")
    public JsonResult testHsi(){
        int i = 1/0;
        return new JsonResult().ERROR();
    }

    @GetMapping("/midR")
    public JsonResult testHsiR(){
        final Object bean =beanFactoryU.getBean(StringUtils.toLowerCaseFirst(TestControl.class.getSimpleName()));
        System.out.println(bean);

        return new JsonResult().ERROR("当前服务已熔断");
    }


    @GetMapping("/mid/{id}")
    @HystrixCommand(defaultFallback = "testHsiR")
    public String testHsi(@PathVariable("id") String id){
        return "路径参数为："+id;
    }

}
