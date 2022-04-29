package com.zhang.mapper;

import com.zhang.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @Author: diexi
 * @Date: 2022/3/27 0:08
 * @ClassName: LoginMapper
 */
@Mapper
@Repository
public interface LoginMapper {

    String getCountUser(User user);

    User findUserById(String userName);
}
