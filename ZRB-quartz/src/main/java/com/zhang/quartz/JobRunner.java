package com.zhang.quartz;

import com.zhang.assertUtils.AssertUtils;
import com.zhang.pojo.quartz.Quartz;
import com.zhang.quartz.manage.QuartzManage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;


import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: diexi
 * @Date: 2022/5/2 17:06
 * @ClassName: JobRunner
 */
@Slf4j
@Component
public class JobRunner implements ApplicationRunner {

    @Resource
    private QuartzManage quartzManage;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("注入定时任务开始");
        //初始化所有定时任务
//        List<Quartz> quartzList = getInitQuartz();
//        Quartz quartz = new Quartz(-1L, "注入定时任务测试", "com.zhang.test.Job", "0/1 * * * * ? *", null);
//        quartzManage.addJob(quartz);
        log.info("注入定时任务完成");
    }

    private List<Quartz> getInitQuartz(List<Quartz> quartzList) {
        AssertUtils.isNull(quartzList.isEmpty(), "诸如定时任务失败，列表为空");
        return quartzList;
    }
}
