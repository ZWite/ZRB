package com.zhang.controller;

import com.alibaba.fastjson.JSONObject;
import com.zhang.ThreadLocal.ContextManager;
import com.zhang.lock.RedissonLock;
import com.zhang.pojo.Dept;
import com.zhang.pojo.User;
import com.zhang.redis.RedisLock;
import com.zhang.redis.RedisUtils;
import com.zhang.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.A;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Author: diexi
 * @Date: 2022/3/13 18:59
 * @ClassName: DeptController
 */
//@UserLoginToken
@RestController
@RequestMapping("/dept")
@Slf4j
public class DeptController {

    @Autowired
    private DeptService service;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private RedisLock redisLock;

//    @Resource(type = RedissonLock.class)
    @Autowired
    private RedissonLock redissonLock;

    @PostMapping("/add")
    public boolean addDept(Dept dept){
        return service.addDept(dept);
    }

    /**
     * @PathVariable 获取地址栏上的参数
     * @RequestBody 只能接受json数据结构
     * @ResponseBody 将返回的数据转化为json
     * @return
     */
//    @UserLoginToken
    @GetMapping("/queryById/{deptNo}")
    public Dept queryById(@PathVariable("deptNo") Long deptNo){
//        String token = ContextManager.getContextData("token");
        User user = ContextManager.getContextData(User.class);
//        Boolean queryById = redisLock.lock("queryById", user.getUserName(), 2000);
        RedissonClient redissonClient = redissonLock.redissonLock();
        RLock getKey = redissonClient.getLock("getKey");
        getKey.lock();
        /**
         * 自旋锁
         * 自旋需要时间限制，超出时间则结束  可能会栈溢出
         * */
//        if (queryById){
//            this.queryById(deptNo);
//        }
        Boolean flag = redisUtils.hasKey(deptNo.toString());
        if(!flag){
            JSONObject jsonObject = (JSONObject) JSONObject.toJSON(service.queryById(deptNo));
            redisUtils.set(deptNo.toString(),jsonObject);
            log.info("查询数据库");
        }
        Dept dept = JSONObject.parseObject(JSONObject.toJSONString(redisUtils.get(deptNo.toString())),Dept.class);
//        redisLock.releaseLock("queryById",user.getUserName());
//        redisUtils.delete("queryById");
//        redisLock.releaseLock("queryById",user.getUserName());
        getKey.unlock();
        return dept;
    }

    @GetMapping("/queryAll")
    public List<Dept> queryAll(){
        return service.queryAll();
    }
    @GetMapping("/querySql/{deptNo}")
    public Dept querySql(@PathVariable("deptNo")String deptNo){
        String sql = "SELECT * FROM DEPT WHERE DEPT_NO = " + deptNo;
        return service.querySql(sql);
    }

    //注册进来的微服务 获取一些服务消息
    @GetMapping("/discovery")
    public Object Discovery(){
        //获取微服务清单列表
        final List<String> services = discoveryClient.getServices();
        System.out.println("discoveryClient ====" );
        final List<ServiceInstance> instances = discoveryClient.getInstances("SPRING-PROVIDER-8001");
        for (ServiceInstance instance : instances) {
            System.out.println(
                    instance.getHost()+"/t"
                    +instance.getScheme()+"/t"
                    +instance.getMetadata()+"/t"
                    +instance.getPort()+"/t"
                    +instance.getUri()+"/t"
                    +instance.getServiceId()+"/t"
            );
        }
        return  this.discoveryClient;
    }

    @GetMapping("fileView")
    public void fileView(@RequestParam String fileUrl, HttpServletResponse response) {
        try {
            service.fileView(fileUrl, response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
