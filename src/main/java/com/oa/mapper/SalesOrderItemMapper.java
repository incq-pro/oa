package com.oa.mapper;

import com.oa.entity.SalesOrderItem;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface SalesOrderItemMapper {
    List<SalesOrderItem> selectByOrderId(Integer orderId);
    void insert(SalesOrderItem item);
    void deleteByOrderId(Integer orderId);
}
