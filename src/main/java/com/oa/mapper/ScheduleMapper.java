package com.oa.mapper;

import com.oa.entity.Schedule;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface ScheduleMapper {
    List<Schedule> selectAll();
    List<Schedule> selectByUserId(Integer userId);
    Schedule selectById(Integer id);
    void insert(Schedule schedule);
    void update(Schedule schedule);
    void delete(Integer id);
}
