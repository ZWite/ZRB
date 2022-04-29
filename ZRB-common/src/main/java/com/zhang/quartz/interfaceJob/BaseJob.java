package com.zhang.quartz.interfaceJob;

import org.quartz.Job;
import org.quartz.JobExecutionContext;

/**
 * job接口
 * @Author: diexi
 * @Date: 2022/4/28 0:33
 * @ClassName: BaseJob
 */
public interface BaseJob extends Job {

    @Override
    void execute(JobExecutionContext context);
}
