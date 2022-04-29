package com.zhang.quartz;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdScheduler;
import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.AdaptableJobFactory;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

/**
 * @Author: diexi
 * @Date: 2022/4/27 23:42
 * @ClassName: QuartzConfigration
 */
@Configuration
public class QuartzConfiguration {

    /**
     * 解决Job注入到Spring Bean为null的问题
     * @return
     */
    @Component("quartzJobFactory")
    public static class QuartzJobFactory extends AdaptableJobFactory {
        private final AutowireCapableBeanFactory capableBeanFactory;

        public QuartzJobFactory(AutowireCapableBeanFactory capableBeanFactory) {
            this.capableBeanFactory = capableBeanFactory;
        }

        @Override
        protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
            //调用父类方法
            Object jobInstance = super.createJobInstance(bundle);
            capableBeanFactory.autowireBean(jobInstance);
            return jobInstance;
        }
    }

    /**
     * 定义quartz调度工厂
     * @param quartzJobFactory
     * @return SchedulerFactoryBean
     * @throws Exception
     */
    @Bean(name = "schedulerFactoryBean")
    public SchedulerFactoryBean schedulerFactory(QuartzJobFactory quartzJobFactory) throws Exception {
        SchedulerFactoryBean bean = new SchedulerFactoryBean();
        // 用于quartz集群,QuartzScheduler 启动时更新己存在的Job
        bean.setOverwriteExistingJobs(true);
        // 延时启动，应用启动1秒后
//        bean.setStartupDelay(5);
        // 注册触发器
//        bean.setTriggers(jobTrigger);
        bean.setJobFactory(quartzJobFactory);
        bean.afterPropertiesSet();
        return bean;
    }

    /**
     * 将Scheduler注入到Srping
     * @param schedulerFactoryBean
     * @return Scheduler
     * @throws SchedulerException
     */
    @Bean(name = "scheduler")
    public Scheduler scheduler(SchedulerFactoryBean  schedulerFactoryBean) throws SchedulerException {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        scheduler.start();
        return scheduler;
    }

}
