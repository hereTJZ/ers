package com.example.ers.biz;

import com.example.ers.entity.User;

public interface IUserBiz {

    User login(String account, String password);

}
