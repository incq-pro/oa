package com.oa.service;

import com.oa.entity.Attendance;
import com.oa.mapper.AttendanceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AttendanceService {
    @Autowired
    private AttendanceMapper attendanceMapper;

    public List<Attendance> getAllAttendances() {
        return attendanceMapper.selectAll();
    }

    public List<Attendance> getAttendancesByPage(int page, int size) {
        int offset = (page - 1) * size;
        return attendanceMapper.selectList(offset, size);
    }

    public int getTotalCount() {
        return attendanceMapper.count();
    }

    public List<Attendance> getAttendancesByUserId(Integer userId) {
        return attendanceMapper.selectByUserId(userId);
    }

    public Attendance getAttendanceById(Integer id) {
        return attendanceMapper.selectById(id);
    }

    public void checkIn(Integer userId) {
        Attendance attendance = new Attendance();
        attendance.setUserId(userId);
        attendance.setType(1);
        attendanceMapper.insert(attendance);
    }

    public void checkOut(Integer userId) {
        Attendance attendance = new Attendance();
        attendance.setUserId(userId);
        attendance.setType(2);
        attendanceMapper.insert(attendance);
    }
}
