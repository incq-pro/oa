package com.oa.mapper;

import com.oa.entity.Department;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface DepartmentMapper {
    List<Department> selectAll();
    Department selectById(Integer id);
    void insert(Department department);
    void update(Department department);
    void delete(Integer id);
}
