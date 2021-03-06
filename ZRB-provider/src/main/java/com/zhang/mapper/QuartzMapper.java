package com.zhang.mapper;

import com.zhang.pojo.quartz.Quartz;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @Author: diexi
 * @Date: 2022/4/28 11:37
 * @ClassName: QuartzMapper
 */
@Mapper
@Repository
public interface QuartzMapper {

    Quartz selectOne(Long id);

    void add(Quartz quartz);

    void update(Quartz quartz);
}
