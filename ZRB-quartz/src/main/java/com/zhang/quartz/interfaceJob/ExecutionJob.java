package com.zhang.quartz.interfaceJob;

import com.zhang.pojo.quartz.Quartz;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.lang.reflect.Method;

/**
 * @Author: diexi
 * @Date: 2022/5/2 15:24
 * @ClassName: ExecutionJob
 */
@Slf4j
@Async
@PersistJobDataAfterExecution //持久化
//@DisallowConcurrentExecution //禁止并发执行
public class ExecutionJob extends QuartzJobBean {

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        Quartz quartz = (Quartz)jobExecutionContext.getMergedJobDataMap().get(Quartz.JOB_KEY);
        long start = System.currentTimeMillis();
        try {
            log.info("任务开始执行，任务名称:"+quartz.getQuartzName());
            Class aClass = Class.forName(quartz.getQuartzExpression());
            Method method  = aClass.getMethod(quartz.getQuartzMethod());
            method.invoke(aClass.newInstance());
            long time = System.currentTimeMillis() - start;
            log.info("执行时间：" +time+"毫秒");
        } catch (Exception e){
            log.info("执行失败");
        }
    }
}
