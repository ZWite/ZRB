package com.zhang.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @Author: diexi
 * @Date: 2022/3/26 19:01
 * @ClassName: User
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true) // 链式写法
public class User {
    private Long id;
    private String userName;
    private String password;
    private String passwordE;
}
