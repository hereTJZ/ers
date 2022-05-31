package com.example.ers.controller;

import com.example.ers.biz.IExperimentBiz;
import com.example.ers.entity.Experiment;
import com.example.ers.entity.Notice;
import com.example.ers.entity.Resource;
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
import java.util.List;

@Controller
public class ExperimentController {
    @Autowired
    private IExperimentBiz experimentBiz;

    /**
     * 实验管理界面
     */
    //访问实验管理页面--模糊搜索通用
    @RequestMapping(value = {"/experiment"}, method = RequestMethod.GET)
    public String fuzzySearchExperimentPage(HttpSession session,
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
            PageInfo<Experiment> pageInfo = experimentBiz.fuzzySearchNotice(
                    content,
                    //时间处理，前端date传来的时间为此字符串格式：2018-06-12
                    Timestamp.valueOf((startTime == null || startTime.equals("")) ? "0000-01-01 00:00:00" : startTime + " 00:00:00"),
                    Timestamp.valueOf((endTime == null || endTime.equals("")) ? "9999-01-01 00:00:00" : endTime + " 00:00:00"),
                    pageNum,
                    pageSize
            );
            List<Experiment> experimentList = pageInfo.getList();
            model.addAttribute("experimentList", experimentList);
            model.addAttribute("totalPages", pageInfo.getPages());
            model.addAttribute("currentPage", pageInfo.getPageNum());
            model.addAttribute("total", pageInfo.getTotal());
            //搜索区恢复
            model.addAttribute("searchContent", content);
            model.addAttribute("startTime", startTime);
            model.addAttribute("endTime", endTime);

        }
        return "experiment";
    }

    //实验预约界面
    @RequestMapping(value = {"/booking"}, method = RequestMethod.GET)
    public String getBookingPage(HttpSession session, Model model) {
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
    @RequestMapping(value = {"/detail/{id}"}, method = {RequestMethod.GET})
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

    /**
     * 预约实验
     */
    @RequestMapping(value = {"/bookExperiment"}, method = RequestMethod.POST)
    @ResponseBody
    public ErsResult bookExperiment(HttpSession session,
                                    Model model,
                                    @RequestParam(defaultValue = "", value = "name", required = false) String name,
                                    @RequestParam(defaultValue = "", value = "content", required = false) String content,
                                    @RequestParam(defaultValue = "", value = "participant", required = false) String participant,
                                    @RequestParam(defaultValue = "", value = "instructor", required = false) String instructor,
                                    @RequestParam(defaultValue = "", value = "relatedKnowledge", required = false) String relatedKnowledge,
                                    @RequestParam(value = "startTime", required = false) String startTime,
                                    @RequestParam(value = "endTime", required = false) String endTime,
                                    @RequestParam(value = "resource", required = false) List<MultipartFile> resource) {

        // Object 类向下强制转型
        User user = (User) session.getAttribute("user");
        // 已登录的情况下
        if (user != null) {
            // 预约次数是否达上限
            if (user.getDayNumber() >= 2) {
                return new ErsResult(500, "forbidden", "今日预约次数已达上限！");
            }
            if (user.getWeekNumber() >= 5) {
                return new ErsResult(500, "forbidden", "本周预约次数已达上限！");
            }

            // `-`

            return new ErsResult(200, "success", "实验预约成功，快前往周历查看吧！\uD83D\uDE04");
        }
        return new ErsResult(500, "forbidden", "当前用户未登录！");
    }
}
