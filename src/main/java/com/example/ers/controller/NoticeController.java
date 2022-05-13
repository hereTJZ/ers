package com.example.ers.controller;

import com.example.ers.biz.INoticeBiz;
import com.example.ers.biz.IUserBiz;
import com.example.ers.entity.Notice;
import com.example.ers.entity.User;
import com.example.ers.utils.ErsResult;
import com.example.ers.utils.Util;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class NoticeController {
    //依赖业务层接口对象
    @Autowired
    private INoticeBiz noticeBiz;

    //访问新闻通知页面
    @RequestMapping(value = {"/news"}, method = RequestMethod.GET)
    public String getNewsPage(HttpSession session,
                              Model model,
                              @RequestParam(defaultValue = "1", value = "pageNum", required = false) int pageNum,
                              @RequestParam(defaultValue = "10", value = "pageSize", required = false) int pageSize) {
        // Object 类向下强制转型
        User user = (User) session.getAttribute("user");
        // 已登录的情况下
        if (user != null) {
            //导航栏用户信息（通用）
            model.addAttribute("user", user);
            model.addAttribute("identity", Util.userIdentity(user.getRole()));
            //分页信息
            PageInfo<Notice> pageInfo = noticeBiz.getNewsPage(pageNum, pageSize);
            List<Notice> noticeList = pageInfo.getList();
            model.addAttribute("noticeList", noticeList);
            model.addAttribute("totalPages", pageInfo.getPages());
            model.addAttribute("currentPage", pageInfo.getPageNum());
        }
        return "news";
    }

    //查询详细通知内容
    @RequestMapping(value = {"/notice/{id}"}, method = RequestMethod.GET)
    @ResponseBody
    public ErsResult getNoticeById(@PathVariable(name = "id") int id, HttpSession session) {
        // Object 类向下强制转型
        User user = (User) session.getAttribute("user");
        // 已登录的情况下
        if (user != null) {
            Notice notice = noticeBiz.getNoticeById(id);
            return new ErsResult(200, "success", notice);
        }
        return new ErsResult(500, "error", "当前用户未登录！");
    }
}
