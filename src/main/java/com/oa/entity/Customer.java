package com.oa.entity;

import lombok.Data;
import java.util.Date;

@Data
public class Customer {
    private Integer id;
    private String name;
    private String contact;
    private String phone;
    private String address;
    private Integer status;
    private Date createTime;
}
