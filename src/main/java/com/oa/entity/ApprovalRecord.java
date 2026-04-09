package com.oa.entity;

import lombok.Data;
import java.util.Date;

@Data
public class ApprovalRecord {
    private Integer id;
    private Integer approvalId;
    private Integer approverId;
    private Integer result;
    private String comment;
    private Date createTime;

    private String approverName;
}
