package com.oa.entity;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class VoucherSummary {
    private Integer id;
    private String voucherNo;
    private String voucherDate;
    private Integer attachmentCount;
    private String makerName;
    private String auditStatusName;
    private String postingStatusName;
    private Integer auditStatus;
    private Integer postingStatus;
    private BigDecimal totalAmount;
    private Integer itemCount;
    private String createTime;
    private String remark;
}
