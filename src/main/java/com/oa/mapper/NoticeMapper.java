package com.oa.mapper;

import com.oa.entity.Notice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface NoticeMapper {
    List<Notice> selectAll();
    List<Notice> selectList(@Param("offset") int offset, @Param("limit") int limit);
    int count();
    Notice selectById(Integer id);
    void insert(Notice notice);
    void update(Notice notice);
    void delete(Integer id);
}
