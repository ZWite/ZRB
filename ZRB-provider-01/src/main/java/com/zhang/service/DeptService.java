package com.zhang.service;

import com.zhang.pojo.Dept;

import java.util.List;

/**
 * @Author: diexi
 * @Date: 2022/3/13 18:57
 * @ClassName: DeptService
 */
public interface DeptService {
    boolean addDept(Dept dept);
    Dept queryById(Long id);
    List<Dept> queryAll();
}
