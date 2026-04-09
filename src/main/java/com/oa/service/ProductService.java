package com.oa.service;

import com.oa.entity.Product;
import com.oa.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductMapper productMapper;

    public List<Product> getAll() {
        return productMapper.selectAll();
    }

    public Product getById(Integer id) {
        return productMapper.selectById(id);
    }

    public void add(Product product) {
        productMapper.insert(product);
    }

    public void update(Product product) {
        productMapper.update(product);
    }

    public void delete(Integer id) {
        productMapper.delete(id);
    }
}
