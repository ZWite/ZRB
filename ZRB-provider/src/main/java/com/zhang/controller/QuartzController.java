package com.zhang.controller;

import com.zhang.mapper.QuartzMapper;
import com.zhang.pojo.quartz.Quartz;
import com.zhang.quartz.manage.QuartzManage;
import com.zhang.service.QuartzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: diexi
 * @Date: 2022/5/2 15:58
 * @ClassName: QuartzController
 */
@RestController
public class QuartzController {

    @Autowired
    private QuartzService service;

    @Autowired
    private QuartzManage manage;

    @PostMapping("/quartz/add")
    public void add(@RequestBody Quartz quartz){
        service.add(quartz);
        manage.addJob(quartz);
    }

    @PostMapping("/quartz/update")
    public void update(@RequestBody Quartz quartz){
        service.update(quartz);
        Quartz quartz1 = service.selectOne(quartz.getId());
        manage.updateJob(quartz1);
    }
}
