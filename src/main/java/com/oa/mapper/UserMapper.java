package com.oa.mapper;

import com.oa.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface UserMapper {
    List<User> selectAll();
    List<User> selectList(@Param("offset") int offset, @Param("limit") int limit);
    int count();
    User selectById(Integer id);
    User selectByUsername(String username);
    void insert(User user);
    void update(User user);
    void delete(Integer id);
}
