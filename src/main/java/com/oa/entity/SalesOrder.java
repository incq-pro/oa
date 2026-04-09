package com.oa.entity;

import lombok.Data;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class SalesOrder {
    private Integer id;
    private String orderNo;
    private Integer customerId;
    private Integer userId;
    private BigDecimal totalAmount;
    private Integer status;
    private Date createTime;

    private String customerName;
    private String userName;
    private List<SalesOrderItem> items;
}
