package com.oa.entity;

import lombok.Data;
import java.util.Date;

@Data
public class Approval {
    private Integer id;
    private Integer typeId;
    private Integer userId;
    private String title;
    private String content;
    private Integer status;
    private Date createTime;
    private Date updateTime;

    private String typeName;
    private String userName;
    private String statusName;
}
