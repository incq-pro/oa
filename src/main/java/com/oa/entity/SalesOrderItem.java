package com.oa.entity;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class SalesOrderItem {
    private Integer id;
    private Integer orderId;
    private Integer productId;
    private BigDecimal price;
    private Integer quantity;
    private BigDecimal subtotal;

    private String productName;
}
