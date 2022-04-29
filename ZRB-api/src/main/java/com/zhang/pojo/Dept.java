package com.zhang.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Author: diexi
 * @Date: 2022/3/13 17:29
 * @ClassName: dept
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true) // 链式写法
public class Dept implements Serializable {
    private Long deptNo;
    private String deptName;
    private String dbSource;

    /*
    链式写法
    new Dept（）;
    dept.set...().set...();
     */
}
