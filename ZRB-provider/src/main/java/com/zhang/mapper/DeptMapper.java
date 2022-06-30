package com.zhang.mapper;

import com.zhang.pojo.Dept;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: diexi
 * @Date: 2022/3/13 18:50
 * @ClassName: DeptMapper
 */
@Mapper
@Repository
public interface DeptMapper {
    public boolean addDept(Dept dept);
    public Dept queryById(Long id);
    public Dept querySql(@Param(value="value") String sql);
    public List<Dept> queryAll();
}
