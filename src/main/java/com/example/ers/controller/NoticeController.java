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
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class NoticeController {
    //依赖业务层接口对象
    @Autowired
    private INoticeBiz noticeBiz;

    //访问新闻通知页面--模糊搜索通用
    @RequestMapping(value = {"/news"}, method = RequestMethod.GET)
    public String fuzzySearchNewsPage(HttpSession session,
                                      Model model,
                                      @RequestParam(defaultValue = "1", value = "pageNum", required = false) int pageNum,
                                      @RequestParam(defaultValue = "10", value = "pageSize", required = false) int pageSize,
                                      @RequestParam(defaultValue = "", value = "content", required = false) String content,
                                      @RequestParam(value = "startTime", required = false) String startTime,
                                      @RequestParam(value = "endTime", required = false) String endTime) {
        // Object 类向下强制转型
        User user = (User) session.getAttribute("user");
        // 已登录的情况下
        if (user != null) {
            //导航栏用户信息（通用）
            model.addAttribute("user", user);
            model.addAttribute("identity", Util.userIdentity(user.getRole()));

            //分页信息
            PageInfo<Notice> pageInfo = noticeBiz.fuzzySearchNotice(
                    content,
                    //时间处理，前端date传来的时间为此字符串格式：2018-06-12
                    Timestamp.valueOf((startTime == null || startTime.equals("")) ? "0000-01-01 00:00:00" : startTime + " 00:00:00"),
                    Timestamp.valueOf((endTime == null || endTime.equals("")) ? "9999-01-01 00:00:00" : endTime + " 00:00:00"),
                    pageNum,
                    pageSize
            );
            List<Notice> noticeList = pageInfo.getList();
            model.addAttribute("noticeList", noticeList);
            model.addAttribute("totalPages", pageInfo.getPages());
            model.addAttribute("currentPage", pageInfo.getPageNum());
            model.addAttribute("total", pageInfo.getTotal());
            //搜索区恢复
            model.addAttribute("searchContent", content);
            model.addAttribute("startTime", startTime);
            model.addAttribute("endTime", endTime);

        }
        return "news";
    }

    //访问公告管理页面--模糊搜索通用
    @RequestMapping(value = {"/notice"}, method = RequestMethod.GET)
    public String fuzzySearchNoticePage(HttpSession session,
                                      Model model,
                                      @RequestParam(defaultValue = "1", value = "pageNum", required = false) int pageNum,
                                      @RequestParam(defaultValue = "10", value = "pageSize", required = false) int pageSize,
                                      @RequestParam(defaultValue = "", value = "content", required = false) String content,
                                      @RequestParam(value = "startTime", required = false) String startTime,
                                      @RequestParam(value = "endTime", required = false) String endTime) {
        // Object 类向下强制转型
        User user = (User) session.getAttribute("user");
        // 已登录的情况下
        if (user != null) {
            //导航栏用户信息（通用）
            model.addAttribute("user", user);
            model.addAttribute("identity", Util.userIdentity(user.getRole()));

            //分页信息
            PageInfo<Notice> pageInfo = noticeBiz.fuzzySearchNotice(
                    content,
                    //时间处理，前端date传来的时间为此字符串格式：2018-06-12
                    Timestamp.valueOf((startTime == null || startTime.equals("")) ? "0000-01-01 00:00:00" : startTime + " 00:00:00"),
                    Timestamp.valueOf((endTime == null || endTime.equals("")) ? "9999-01-01 00:00:00" : endTime + " 00:00:00"),
                    pageNum,
                    pageSize
            );
            List<Notice> noticeList = pageInfo.getList();
            model.addAttribute("noticeList", noticeList);
            model.addAttribute("totalPages", pageInfo.getPages());
            model.addAttribute("currentPage", pageInfo.getPageNum());
            model.addAttribute("total", pageInfo.getTotal());
            //搜索区恢复
            model.addAttribute("searchContent", content);
            model.addAttribute("startTime", startTime);
            model.addAttribute("endTime", endTime);

        }
        return "notice";
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

    // 添加公告
    @RequestMapping(value = {"/addNotice"}, method = RequestMethod.POST)
    @ResponseBody
    public ErsResult addNotice(HttpSession session,
                               @RequestParam(defaultValue = "", value = "title", required = false) int title,
                               @RequestParam(defaultValue = "", value = "content", required = false) int content,
                               @RequestParam(value = "pictures", required = false) MultipartFile pictures) {
        // Object 类向下强制转型
        User user = (User) session.getAttribute("user");
        // 已登录的情况下
        if (user != null) {
            Notice notice = new Notice();
            noticeBiz.addNotice(notice);
            return new ErsResult(200, "success", notice);
        }
        return new ErsResult(500, "error", "当前用户未登录！");
    }
}
