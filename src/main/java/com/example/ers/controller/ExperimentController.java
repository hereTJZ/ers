package com.example.ers.controller;

import com.example.ers.biz.IExperimentBiz;
import com.example.ers.entity.Experiment;
import com.example.ers.entity.Notice;
import com.example.ers.entity.Resource;
import com.example.ers.entity.User;
import com.example.ers.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ExperimentController {
    @Autowired
    private IExperimentBiz experimentBiz;

    //实验预约界面
    @RequestMapping(value = {"/booking"}, method = RequestMethod.GET)
    public String getBookingPage(HttpSession session, Model model){
        // Object 类向下强制转型
        User user = (User) session.getAttribute("user");
        // 已登录的情况下
        if (user != null) {
            //导航栏用户信息（通用）
            model.addAttribute("user", user);
            model.addAttribute("identity", Util.userIdentity(user.getRole()));

        }
        return "booking";
    }

    //实验详细页面
    //params,headers参数表示request中必须包这些参数的值，才让该方法处理请求
    @RequestMapping(value = {"/detail/{id}"},
            method = {RequestMethod.GET})
    public String demo1(@PathVariable(name = "id") int id, HttpSession session, Model model) {
        // Object 类向下强制转型
        User user = (User) session.getAttribute("user");
        // 已登录的情况下，向model注入相关数据
        if (user != null) {
            // 用户信息也需要传入
            model.addAttribute("user", user);
            model.addAttribute("identity", Util.userIdentity(user.getRole()));
            // 实验信息
            Experiment experiment = experimentBiz.getExperimentById(id);
            // System.out.println(experiment);
            model.addAttribute("experiment", experiment);
            model.addAttribute("state", Util.experimentState(experiment.getState()));

        }
        // 返回页面
        return "detail";
    }
}
