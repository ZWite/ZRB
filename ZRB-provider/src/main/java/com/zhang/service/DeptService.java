package com.zhang.service;

import com.zhang.mapper.DeptMapper;
import com.zhang.pojo.Dept;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Author: diexi
 * @Date: 2022/3/13 18:57
 * @ClassName: DeptService
 */
public interface DeptService {
    boolean addDept(Dept dept);
    Dept queryById(Long id);
    Dept querySql(String sql);
    List<Dept> queryAll();

    void fileView(String fileUrl, HttpServletResponse response) throws Exception;
}
