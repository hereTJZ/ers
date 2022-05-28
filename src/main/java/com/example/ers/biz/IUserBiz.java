package com.example.ers.biz;

import com.example.ers.entity.Resource;
import com.example.ers.entity.User;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

public interface IUserBiz {

    // 获取登录用户信息
    User login(String account, String password);

    // 通过id找到用户
    User findUserById(int id);

    // 通过账号（手机号或邮箱）找到用户
    User findUserByAccount(String account);

    // 发送注册验证码邮件
    void sendEmail(String targetEmail, String code);

    // 注册用户
    int register(User user);

    // 用户修改个人信息
    int resetUserInfo(User user);

    // 获取用户信息
    PageInfo<User> getUserPage(int role, int pageNum, int pageSize, String content);

    // 通过id删除用户
    int deleteUserById(int id);

    // 修改用户密码
    int resetUserPassword(User user);

    // 修改用户头像
    int changeAvatar(User user);
}
