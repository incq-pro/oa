package com.oa.controller;

import com.oa.entity.Schedule;
import com.oa.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/schedule")
public class ScheduleController {
    @Autowired
    private ScheduleService scheduleService;

    @GetMapping("/list")
    public String list(Model model) {
        List<Schedule> schedules = scheduleService.getSchedulesByUserId(1);
        model.addAttribute("schedules", schedules);
        return "schedule/list";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("schedule", new Schedule());
        return "schedule/edit";
    }

    @PostMapping("/save")
    public String save(Schedule schedule) {
        schedule.setUserId(1);
        if (schedule.getId() == null) {
            scheduleService.addSchedule(schedule);
        } else {
            scheduleService.updateSchedule(schedule);
        }
        return "redirect:/schedule/list";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("schedule", scheduleService.getScheduleById(id));
        return "schedule/edit";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        scheduleService.deleteSchedule(id);
        return "redirect:/schedule/list";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Integer id, Model model) {
        model.addAttribute("schedule", scheduleService.getScheduleById(id));
        return "schedule/schedule_detail";
    }
}
