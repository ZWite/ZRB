package com.zhang.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Author: diexi
 * @Date: 2022/3/26 19:01
 * @ClassName: User
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true) // 链式写法
public class User implements Serializable {

    @NonNull
    private Long id;
    @NonNull
    private String userName;
    @NonNull
    private String password;
    @NonNull
    private String passwordE;
}
