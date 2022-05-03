package com.zhang.quartz.manage;

import com.zhang.pojo.quartz.Quartz;
import com.zhang.quartz.interfaceJob.ExecutionJob;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.Executor;
import org.quartz.*;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

/**
 * 定时任务增删改
 * @Author: diexi
 * @Date: 2022/5/2 15:16
 * @ClassName: QuartzManage
 */

@Slf4j
@Component
public class QuartzManage {

    private static final String JOB_NAME = "TASK_";

    @Resource(name = "scheduler")
    private Scheduler scheduler;

    public void addJob(Quartz quartz){
        try {
            deleteJob(quartz);
            //构建job信息
            JobDetail jobDetail = JobBuilder.newJob(ExecutionJob.class)
                    .withDescription(JOB_NAME+ quartz.getId()).build();
            Trigger cronTrigger = TriggerBuilder.newTrigger()
                    .withIdentity(JOB_NAME+ quartz.getId())
                    .startNow()
                    .withSchedule(CronScheduleBuilder.cronSchedule(quartz.getQuartzCorn()))
                    .build();
            cronTrigger.getJobDataMap().put(Quartz.JOB_KEY,quartz);
            //重启启动时间
            ((CronTriggerImpl) cronTrigger).setStartTime(new Date());

            //执行定时任务
            scheduler.scheduleJob(jobDetail,cronTrigger);
        } catch (Exception e){
            log.info("创建定时任务失败");
        } finally {
            log.info("创建定时任务成功");
        }
    }

    public void updateJob(Quartz quartz){
        try {
            deleteJob(quartz);
            TriggerKey triggerKey = TriggerKey.triggerKey(JOB_NAME+quartz.getId());
            CronTrigger cronTrigger = (CronTrigger)scheduler.getTrigger(triggerKey);
            if (cronTrigger == null ){
                //不存在就创建一个定时任务
                addJob(quartz);
                cronTrigger = (CronTrigger)scheduler.getTrigger(triggerKey);
            }
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(quartz.getQuartzCorn());
            cronTrigger = cronTrigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder)
                    .build();
            cronTrigger.getJobDataMap().put(Quartz.JOB_KEY,quartz);
            //重启启动时间
            ((CronTriggerImpl) cronTrigger).setStartTime(new Date());

            //执行定时任务
            scheduler.rescheduleJob(triggerKey,cronTrigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }


    public void deleteJob(Quartz quartz){
        try {
            JobKey jobKey = JobKey.jobKey(JOB_NAME+quartz.getId());
            if (jobKey != null) {
                scheduler.pauseJob(jobKey);
                scheduler.deleteJob(jobKey);
            }
        } catch (SchedulerException e) {
            log.info("删除定时任务失败");
        }
    }

    public void resumeJob(Quartz quartz){
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(JOB_NAME+quartz.getId());
            CronTrigger cronTrigger = (CronTrigger)scheduler.getTrigger(triggerKey);
            if (cronTrigger == null){
                addJob(quartz);
            }
            JobKey jobKey = JobKey.jobKey(JOB_NAME+quartz.getId());
            scheduler.resumeJob(jobKey);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    public void runJobNow(Quartz quartz){
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(JOB_NAME+quartz.getId());
            CronTrigger cronTrigger = (CronTrigger)scheduler.getTrigger(triggerKey);
            if (cronTrigger == null){
                addJob(quartz);
            }
            JobDataMap jobDataMap = new JobDataMap();
            jobDataMap.put(Quartz.JOB_KEY,quartz);
            JobKey jobKey = JobKey.jobKey(JOB_NAME+quartz.getId());
            scheduler.resumeJob(jobKey);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    public void pauseJob(Quartz quartz){
        try {
            JobKey jobKey = JobKey.jobKey(JOB_NAME+quartz.getId());
            scheduler.pauseJob(jobKey);
        } catch (SchedulerException e) {
            log.error("定时任务停止失败");
            e.printStackTrace();
        }
    }
}
