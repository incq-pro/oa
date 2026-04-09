package com.oa.mapper;

import com.oa.entity.Product;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface ProductMapper {
    List<Product> selectAll();
    Product selectById(Integer id);
    void insert(Product product);
    void update(Product product);
    void delete(Integer id);
}
