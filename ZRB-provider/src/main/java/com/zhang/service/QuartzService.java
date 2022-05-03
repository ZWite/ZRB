package com.zhang.service;

import com.zhang.pojo.quartz.Quartz;

/**
 * @Author: diexi
 * @Date: 2022/4/28 11:37
 * @ClassName: QuartzMapper
 */
public interface QuartzService {

    Quartz selectOne(Long id);

    void add(Quartz quartz);

    void update(Quartz quartz);
}
