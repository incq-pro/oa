package com.oa.mapper;

import com.oa.entity.ApprovalType;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface ApprovalTypeMapper {
    List<ApprovalType> selectAll();
    ApprovalType selectById(Integer id);
}
