package com.example.ers.controller;

import com.example.ers.biz.IUserBiz;
import com.example.ers.entity.User;
import com.example.ers.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {
    //依赖业务层接口对象
    @Autowired
    private IUserBiz userBiz;

    /**
     * 首页界面
     */
    @RequestMapping(value = {"/home"})
    public String homePage(HttpSession session, Model model) {
        // Object 类向下强制转型
        User user = (User) session.getAttribute("user");
        // 已登录的情况下
        if (user != null) {
            model.addAttribute("user", user);
            model.addAttribute("identity", Util.userIdentity(user.getRole()));
        }
        return "home";
    }
}
