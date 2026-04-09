package com.oa.entity;

import lombok.Data;
import java.util.Date;

@Data
public class Department {
    private Integer id;
    private String name;
    private Integer parentId;
    private Date createTime;
}
