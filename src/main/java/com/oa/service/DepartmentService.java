package com.oa.service;

import com.oa.entity.Department;
import com.oa.mapper.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentMapper departmentMapper;

    public List<Department> getAllDepartments() {
        return departmentMapper.selectAll();
    }

    public Department getDepartmentById(Integer id) {
        return departmentMapper.selectById(id);
    }

    public void addDepartment(Department department) {
        departmentMapper.insert(department);
    }

    public void updateDepartment(Department department) {
        departmentMapper.update(department);
    }

    public void deleteDepartment(Integer id) {
        departmentMapper.delete(id);
    }
}
