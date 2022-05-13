package com.example.ers.biz;

import com.example.ers.entity.User;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

public interface IUserBiz {

    User login(String account, String password);

    User findUserById(int id);

    User findUserByAccount(String account);

    void sendEmail(String targetEmail, String code);

    int register(String phone, String email, String password, String realName, int roleId, Date time);
}
