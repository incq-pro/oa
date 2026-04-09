package com.oa.mapper;

import com.oa.entity.SalesOrder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface SalesOrderMapper {
    List<SalesOrder> selectAll();
    List<SalesOrder> selectList(@Param("offset") int offset, @Param("limit") int limit);
    int count();
    SalesOrder selectById(Integer id);
    void insert(SalesOrder order);
    void update(SalesOrder order);
    void delete(Integer id);
    List<SalesOrder> selectWithDetails();
}
