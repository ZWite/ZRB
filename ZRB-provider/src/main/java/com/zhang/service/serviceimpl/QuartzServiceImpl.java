package com.zhang.service.serviceimpl;

import com.zhang.mapper.DeptMapper;
import com.zhang.mapper.QuartzMapper;
import com.zhang.pojo.quartz.Quartz;
import com.zhang.service.QuartzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: diexi
 * @Date: 2022/5/2 15:57
 * @ClassName: QuartzServiceImpl
 */
@Service
public class QuartzServiceImpl implements QuartzService {

    @Autowired
    private QuartzMapper mapper;

    @Override
    public Quartz selectOne(Long id) {
        Quartz quartz = mapper.selectOne(id);
        return quartz;
    }

    @Override
    public void add(Quartz quartz) {
        mapper.add(quartz);
    }

    @Override
    public void update(Quartz quartz) {
        mapper.update(quartz);
    }
}
