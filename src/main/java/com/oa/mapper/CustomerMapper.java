package com.oa.mapper;

import com.oa.entity.Customer;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface CustomerMapper {
    List<Customer> selectAll();
    Customer selectById(Integer id);
    void insert(Customer customer);
    void update(Customer customer);
    void delete(Integer id);
}
