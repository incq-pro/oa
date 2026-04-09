package com.oa.entity;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class SubjectSummary {
    private Integer subjectId;
    private String subjectCode;
    private String subjectName;
    private String balanceDirection;
    private BigDecimal debitAmount;
    private BigDecimal creditAmount;
    private BigDecimal balance;
    private Integer voucherCount;
}
