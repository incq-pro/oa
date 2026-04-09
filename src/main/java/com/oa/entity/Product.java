package com.oa.entity;

import lombok.Data;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class Product {
    private Integer id;
    private String name;
    private String code;
    private BigDecimal price;
    private Integer stock;
    private String unit;
    private Integer status;
    private Date createTime;
}
