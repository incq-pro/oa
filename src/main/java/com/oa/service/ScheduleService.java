package com.oa.service;

import com.oa.entity.Schedule;
import com.oa.mapper.ScheduleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ScheduleService {
    @Autowired
    private ScheduleMapper scheduleMapper;

    public List<Schedule> getAllSchedules() {
        return scheduleMapper.selectAll();
    }

    public List<Schedule> getSchedulesByUserId(Integer userId) {
        return scheduleMapper.selectByUserId(userId);
    }

    public Schedule getScheduleById(Integer id) {
        return scheduleMapper.selectById(id);
    }

    public void addSchedule(Schedule schedule) {
        scheduleMapper.insert(schedule);
    }

    public void updateSchedule(Schedule schedule) {
        scheduleMapper.update(schedule);
    }

    public void deleteSchedule(Integer id) {
        scheduleMapper.delete(id);
    }
}
