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

    @Override
    public User login(String account, String password) {
        return userMapper.login(account, password);
    }

    @Override
    public User findUserById(int id) {
        return userMapper.findUserById(id);
    }

    @Override
    public User findUserByAccount(String account) {
        return userMapper.findUserByAccount(account);
    }

    @Override
    public void sendEmail(String targetEmail, String code) {
        // 简单文本邮件发送
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        try {
            // html邮件发送
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

    @Override
    public int register(String phone, String email, String password, String realName, int roleId, Date time) {
        return userMapper.register(phone, email, password, realName, roleId, time);
    }


}
