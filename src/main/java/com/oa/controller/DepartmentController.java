package com.oa.controller;

import com.oa.entity.Department;
import com.oa.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/dept")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("depts", departmentService.getAllDepartments());
        return "dept/list";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("dept", new Department());
        return "dept/edit";
    }

    @PostMapping("/save")
    public String save(Department department) {
        if (department.getId() == null) {
            departmentService.addDepartment(department);
        } else {
            departmentService.updateDepartment(department);
        }
        return "redirect:/dept/list";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("dept", departmentService.getDepartmentById(id));
        return "dept/edit";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        departmentService.deleteDepartment(id);
        return "redirect:/dept/list";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Integer id, Model model) {
        model.addAttribute("dept", departmentService.getDepartmentById(id));
        return "dept/dept_detail";
    }
}
