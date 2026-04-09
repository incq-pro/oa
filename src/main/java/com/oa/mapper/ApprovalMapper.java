package com.oa.mapper;

import com.oa.entity.Approval;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface ApprovalMapper {
    List<Approval> selectAll();
    List<Approval> selectByUserId(Integer userId);
    Approval selectById(Integer id);
    void insert(Approval approval);
    void update(Approval approval);
    void delete(Integer id);
}
