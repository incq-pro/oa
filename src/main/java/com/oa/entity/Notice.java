package com.oa.entity;

import lombok.Data;
import java.util.Date;

@Data
public class Notice {
    private Integer id;
    private String title;
    private String content;
    private Integer authorId;
    private Date createTime;
    private Date updateTime;

    private String authorName;
}
