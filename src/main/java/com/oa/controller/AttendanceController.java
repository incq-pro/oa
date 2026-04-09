package com.oa.controller;

import com.oa.entity.Attendance;
import com.oa.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/attendance")
public class AttendanceController {
    @Autowired
    private AttendanceService attendanceService;

    @GetMapping("/list")
    public String list(@RequestParam(defaultValue = "1") int page,
                      @RequestParam(defaultValue = "10") int size,
                      Model model) {
        model.addAttribute("attendances", attendanceService.getAttendancesByPage(page, size));
        model.addAttribute("currentPage", page);
        model.addAttribute("totalCount", attendanceService.getTotalCount());
        model.addAttribute("pageSize", size);
        return "attendance/list";
    }

    @GetMapping("/checkin")
    public String checkIn() {
        attendanceService.checkIn(1);
        return "redirect:/attendance/list";
    }

    @GetMapping("/checkout")
    public String checkOut() {
        attendanceService.checkOut(1);
        return "redirect:/attendance/list";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Integer id, Model model) {
        model.addAttribute("attendance", attendanceService.getAttendanceById(id));
        return "attendance/attendance_detail";
    }
}
