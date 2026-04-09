package com.oa.entity;

import lombok.Data;
import java.math.BigDecimal;
import java.util.List;

@Data
public class Voucher {
    private Integer id;
    private String voucherNo;
    private String voucherDate;
    private Integer attachmentCount;
    private Integer makerId;
    private Integer checkerId;
    private Integer posterId;
    private Integer auditStatus;
    private Integer postingStatus;
    private BigDecimal totalAmount;
    private String remark;
    private String createTime;
    private String updateTime;

    private String makerName;
    private String checkerName;
    private String posterName;
    private String auditStatusName;
    private String postingStatusName;
    private List<VoucherItem> items;
}
