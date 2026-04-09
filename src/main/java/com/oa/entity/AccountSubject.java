package com.oa.entity;

import lombok.Data;

@Data
public class AccountSubject {
    private Integer id;
    private String subjectCode;
    private String subjectName;
    private Integer parentId;
    private String balanceDirection;
    private Integer level;
    private Integer isLeaf;
    private Integer status;
    private String createTime;
    private String updateTime;

    private String parentName;
    private String directionName;
}
