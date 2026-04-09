package com.oa.entity;

import lombok.Data;
import java.util.Date;

@Data
public class Attendance {
    private Integer id;
    private Integer userId;
    private Integer type;
    private Date createTime;

    private String userName;
    private String typeName;
}
