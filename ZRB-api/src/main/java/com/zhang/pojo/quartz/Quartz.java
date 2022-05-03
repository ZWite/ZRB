package com.zhang.pojo.quartz;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @Author: diexi
 * @Date: 2022/4/28 11:05
 * @ClassName: Quartz
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Quartz implements Serializable {

    public static final String JOB_KEY = "JOB_KEY";

    private Long id;
    private String quartzName;
    private String quartzExpression;
    private String quartzCorn;
    private Timestamp createDate;
    private String quartzMethod;
}
