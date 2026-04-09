package com.oa.mapper;

import com.oa.entity.Attendance;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface AttendanceMapper {
    List<Attendance> selectAll();
    List<Attendance> selectList(@Param("offset") int offset, @Param("limit") int limit);
    int count();
    List<Attendance> selectByUserId(Integer userId);
    Attendance selectById(Integer id);
    void insert(Attendance attendance);
}
