package com.catvgd.springbootdemo.biz.calc.service;

import com.catvgd.springbootdemo.biz.calc.mapper.UserMapper;
import com.catvgd.springbootdemo.biz.calc.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public List<User> getUserList() {
        List<User> userList = userMapper.getUserList();
        return userList;
    }

    public User getUserById(long id) {
        User user = userMapper.getUserById(id);
        return user;
    }

}
