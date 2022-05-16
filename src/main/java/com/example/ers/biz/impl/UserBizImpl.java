package com.example.ers.biz.impl;

import com.example.ers.biz.IUserBiz;
import com.example.ers.dao.UserMapper;
import com.example.ers.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Date;

@Service
public class UserBizImpl implements IUserBiz {
    //依赖持久层接口
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private JavaMailSender javaMailSender;

    // 获取登录用户信息
    @Override
    public User login(String account, String password) {
        return userMapper.login(account, password);
    }

    // 通过id找到用户
    @Override
    public User findUserById(int id) {
        return userMapper.findUserById(id);
    }

    // 通过账号（手机号或邮箱）找到用户
    @Override
    public User findUserByAccount(String account) {
        return userMapper.findUserByAccount(account);
    }

    // 发送注册验证码邮件
    @Override
    public void sendEmail(String targetEmail, String code) {
        // 简单文本邮件发送
//        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        try {
            // 复杂html邮件发送
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom("smart_ERS@163.com" + "(验证码)");
            helper.setTo(targetEmail);
            helper.setSubject("在线开放实验预约与自动授权系统-验证码");
            helper.setText("This is your verification code：<br> <a>" +
                            code +
                            "</a href = \"\"><br>Please note the confidentiality.<br>" +
                            "If it is not your operation, please ignore.",
                    true);

            //添加附件(相对路径)
//            File f1 = new File("src\\main\\resources\\static\\img\\2.png");
//            File f2 = new File("src\\main\\resources\\static\\img\\3.png");
//            helper.addAttachment(f1.getName(), f1);
//            helper.addAttachment("地图.png", f2);

            // 发送
            javaMailSender.send(message);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    // 注册用户
    @Override
    public int register(String phone, String email, String password, String realName, int roleId, Date time) {
        return userMapper.register(phone, email, password, realName, roleId, time);
    }

    // 用户修改个人信息
    @Override
    public int resetUserInfo(User user) {
        return 0;
    }

    // 通过id删除用户
    @Override
    public int deleteUserById(int id) {
        return 0;
    }


}
