package com.zhang.service.serviceimpl;

import com.zhang.mapper.DeptMapper;
import com.zhang.pojo.Dept;
import com.zhang.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: diexi
 * @Date: 2022/3/13 18:58
 * @ClassName: DeptServiceImpl
 */
@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper mapper;

    @Override
    public boolean addDept(Dept dept) {
        return mapper.addDept(dept);
    }

    @Override
    public Dept queryById(Long id) {
        return mapper.queryById(id);
    }

    @Override
    public Dept querySql(String sql) {
        return mapper.querySql(sql);
    }

    @Override
    public List<Dept> queryAll() {
        return mapper.queryAll();
    }
}
