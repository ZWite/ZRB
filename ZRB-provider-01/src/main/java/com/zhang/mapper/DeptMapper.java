package com.zhang.mapper;

import com.zhang.pojo.Dept;
import org.apache.ibatis.annotations.Mapper;
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
    boolean addDept(Dept dept);
    Dept queryById(Long id);
    List<Dept> queryAll();
}
