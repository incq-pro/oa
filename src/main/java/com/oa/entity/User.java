package com.oa.entity;

import lombok.Data;
import java.util.Date;

@Data
public class User {
    private Integer id;
    private String username;
    private String password;
    private String realName;
    private String email;
    private String phone;
    private Integer deptId;
    private String position;
    private String role;
    private Integer status;
    private Date createTime;

    private String deptName;
}
