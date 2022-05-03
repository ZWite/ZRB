package com.zhang.quartz;

import com.zhang.quartz.interfaceJob.BaseJob;
import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.springframework.stereotype.Component;

/**
 * @Author: diexi
 * @Date: 2022/4/28 0:35
 * @ClassName: JobSerive
 */

/**
 * @DisallowConcurrentExecution
 * 不允许并发执行 也就是说当Job的执行时间（如执行完需要30s）大于job的执行时间间隔（如10s）
 * 默认情况下，quartz为了能让job按照预定的时间间隔执行，会马上启用新的线程执行job。
 */
@Slf4j
@Component
@DisallowConcurrentExecution
public class JobService implements BaseJob {

    @Override
    public void execute(JobExecutionContext context) {
        log.info("test job----PreviousFireTime={},NextFireTime={},FireTime={}",
                context.getPreviousFireTime(), context.getNextFireTime(), context.getFireTime());

    }
}
