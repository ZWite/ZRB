package com.zhang.pojo.quartz;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

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
public class Quartz {
    private Long id;
    private String quartzName;
    private String quartzExpression;
    private String quartzCorn;
    private Timestamp createDate;
}
