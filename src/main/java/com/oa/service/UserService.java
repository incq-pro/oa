package com.oa.service;

import com.oa.entity.User;
import com.oa.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public List<User> getAllUsers() {
        return userMapper.selectAll();
    }

    public List<User> getUsersByPage(int page, int size) {
        int offset = (page - 1) * size;
        return userMapper.selectList(offset, size);
    }

    public int getTotalCount() {
        return userMapper.count();
    }

    public User getUserById(Integer id) {
        return userMapper.selectById(id);
    }

    public User getUserByUsername(String username) {
        return userMapper.selectByUsername(username);
    }

    public void addUser(User user) {
        userMapper.insert(user);
    }

    public void updateUser(User user) {
        userMapper.update(user);
    }

    public void deleteUser(Integer id) {
        userMapper.delete(id);
    }
}
