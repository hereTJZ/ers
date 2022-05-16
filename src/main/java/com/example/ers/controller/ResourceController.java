package com.example.ers.controller;

import com.example.ers.biz.IResourceBiz;
import com.example.ers.entity.Notice;
import com.example.ers.entity.Resource;
import com.example.ers.entity.User;
import com.example.ers.utils.Util;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ResourceController {
    @Autowired
    private IResourceBiz resourceBiz;

    //访问资源页面
    @RequestMapping(value = {"/resource"}, method = RequestMethod.GET)
    public String getResourcePage(HttpSession session,
                                  Model model,
                                  @RequestParam(defaultValue = "1", value = "pageNum", required = false) int pageNum,
                                  @RequestParam(defaultValue = "10", value = "pageSize", required = false) int pageSize){
        // Object 类向下强制转型
        User user = (User) session.getAttribute("user");
        // 已登录的情况下
        if (user != null) {
            //导航栏用户信息（通用）
            model.addAttribute("user", user);
            model.addAttribute("identity", Util.userIdentity(user.getRole()));

            //分页信息
            PageInfo<Resource> pageInfo = resourceBiz.getResourcePage(pageNum, pageSize);
            List<Resource> resourceList = pageInfo.getList();
            model.addAttribute("resourceList", resourceList);
            model.addAttribute("totalPages", pageInfo.getPages());
            model.addAttribute("currentPage", pageInfo.getPageNum());

        }
        return "resource";
    }
}
