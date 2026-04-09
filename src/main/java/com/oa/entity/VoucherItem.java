package com.oa.entity;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class VoucherItem {
    private Integer id;
    private Integer voucherId;
    private Integer subjectId;
    private String direction;
    private BigDecimal amount;
    private String summary;
    private Integer seq;
    private String createTime;

    private String subjectCode;
    private String subjectName;
    private String directionName;
}
