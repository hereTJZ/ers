package com.example.ers.dao;

import com.example.ers.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;

@Mapper
public interface UserMapper {
    /**
     * 获取登录用户信息
     * @param account
     * @param password
     * @return
     */
    User login(String account, String password);

    /**
     * 通过 id 找到用户
     * @param id
     * @return
     */
    User findUserById(int id);

    /**
     * 通过账号（手机号或邮箱）找到用户
     * @param account
     * @return
     */
    User findUserByAccount(String account);

    /**
     * 注册用户
     * @param phone
     * @param email
     * @param password
     * @param realName
     * @param role
     * @param registerTime
     * @return
     */
    int register(String phone, String email, String password, String realName, int role, Date registerTime);
}
