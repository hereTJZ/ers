package com.example.ers.dao;

import com.example.ers.entity.Resource;
import com.example.ers.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

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
     * @param user
     * @return
     */
    int register(User user);

    /**
     * 用户修改个人信息
     * @param user
     * @return
     */
    int resetUserInfo(User user);

    /**
     * 通过id删除用户
     * @param id
     * @return
     */
    int deleteUserById(int id);

    /**
     * 获取所有某身份用户
     */
    List<User> getUsers(int role, String content);

    /**
     * 修改用户密码
     */
    int resetUserPassword(User user);

    /**
     * 修改用户头像地址
     */
    int changeAvatarAddress(User user);
}
