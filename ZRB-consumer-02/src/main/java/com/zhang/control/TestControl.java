package com.zhang.control;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.zhang.result.JsonResult;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/mid")
    @HystrixCommand(defaultFallback = "testHsiR")
    public JsonResult testHsi(){
        JsonResult jsonResult = new JsonResult();
        return jsonResult.SUCCESS();
    }
    public JsonResult testHsiR(){
        return new JsonResult().ERROR("当前服务已熔断");
    }


    @GetMapping("/midA")
    @HystrixCommand(defaultFallback = "testHsiR")
    public JsonResult testHsiA(){
        JsonResult jsonResult = new JsonResult();
        return jsonResult.SUCCESS();
    }
}
