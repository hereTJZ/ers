package com.example.ers.controller;

import com.example.ers.biz.IResourceBiz;
import com.example.ers.entity.User;
import com.example.ers.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
public class ResourceController {
    @Autowired
    private IResourceBiz resourceBiz;

    //访问资源页面
    @RequestMapping(value = {"/resource"}, method = RequestMethod.GET)
    public String getResourcePage(HttpSession session, Model model){
        // Object 类向下强制转型
        User user = (User) session.getAttribute("user");
        // 已登录的情况下
        if (user != null) {
            //导航栏用户信息（通用）
            model.addAttribute("user", user);
            model.addAttribute("identity", Util.userIdentity(user.getRole()));

        }
        return "resource";
    }
}
