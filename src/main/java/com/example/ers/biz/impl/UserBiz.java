package com.example.ers.biz.impl;

import com.example.ers.biz.IUserBiz;
import com.example.ers.dao.UserMapper;
import com.example.ers.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserBiz  implements IUserBiz {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(String account, String password) {
        return userMapper.login(account, password);
    }
}
