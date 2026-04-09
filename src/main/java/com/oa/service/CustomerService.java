package com.oa.service;

import com.oa.entity.Customer;
import com.oa.mapper.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private CustomerMapper customerMapper;

    public List<Customer> getAll() {
        return customerMapper.selectAll();
    }

    public Customer getById(Integer id) {
        return customerMapper.selectById(id);
    }

    public void add(Customer customer) {
        customerMapper.insert(customer);
    }

    public void update(Customer customer) {
        customerMapper.update(customer);
    }

    public void delete(Integer id) {
        customerMapper.delete(id);
    }
}
