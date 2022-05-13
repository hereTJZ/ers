package com.example.ers.controller;

import com.example.ers.biz.IExperimentBiz;
import com.example.ers.biz.INoticeBiz;
import com.example.ers.biz.IResourceBiz;
import com.example.ers.biz.IUserBiz;
import com.example.ers.entity.Experiment;
import com.example.ers.entity.Notice;
import com.example.ers.entity.Resource;
import com.example.ers.entity.User;
import com.example.ers.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class HomeController {
    //依赖业务层接口对象
    @Autowired
    private IUserBiz userBiz;
    @Autowired
    private INoticeBiz noticeBiz;
    @Autowired
    private IResourceBiz resourceBiz;
    @Autowired
    private IExperimentBiz experimentBiz;

    /**
     * 首页界面
     */
    @RequestMapping(value = {"/home"})
    public String homePage(HttpSession session, Model model) {
        // Object 类向下强制转型
        User user = (User) session.getAttribute("user");
        // 已登录的情况下
        if (user != null) {
            //导航栏用户信息（通用）
            model.addAttribute("user", user);
            model.addAttribute("identity", Util.userIdentity(user.getRole()));
            // 新闻公告板块
            List<Notice> noticeList = noticeBiz.getFiveNews();
            model.addAttribute("noticeList", noticeList);
            // 资源网站板块
            List<Resource> resourceList = resourceBiz.getFiveResources();
            model.addAttribute("resourceList", resourceList);
            // 近期实验板块
            List<Experiment> experimentList = experimentBiz.getFiveExperiments();
            model.addAttribute("experimentList", experimentList);

        }
        return "home";
    }
}
