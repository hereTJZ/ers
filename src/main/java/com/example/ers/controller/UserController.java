package com.example.ers.controller;

import com.example.ers.biz.IUserBiz;
import com.example.ers.entity.User;
import com.example.ers.utils.ErsResult;
import com.example.ers.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {
    //依赖业务层接口对象
    @Autowired
    private IUserBiz userBiz;

    /**
     * 登录界面
     */
    @RequestMapping(value = {"/", "/login"})
    public String loginPage(Model model, HttpSession session) {
        return "login";
    }

    /**
     * 注册界面
     */
    @RequestMapping(value = {"/register"})
    public String registPage() {
        return "register";
    }


    /**
     * 登录账户密码验证
     */
    @RequestMapping(value = {"/executeLogin"}, method = RequestMethod.POST)
    @ResponseBody
    public ErsResult loginCheck(Model model,
                                HttpSession session,
                                @RequestParam("account") String account,
                                @RequestParam("password") String password) {
        User userTemp = (User) session.getAttribute("user");
        // 首先核验当前是否已经登录
        if (userTemp != null) {
            return new ErsResult(400, "error", "当前用户已经登录");
        }
        User user = userBiz.login(account, password);
        // 用户登录成功
        if (user != null) {
            System.out.println(user.toString());
            // 将用户信息放入session
            session.setAttribute("user", user);
            return new ErsResult(200, "success", user);
            // 用户登录失败
        } else {
            return new ErsResult(500, "error", "账号或密码错误");
        }
    }

    /**
     * 登录状态核验
     */
    @RequestMapping("/checkLogin")
    @ResponseBody
    public ErsResult checkLogin(HttpSession session) {
        System.out.println("执行登录状态核验~");
        ErsResult result = new ErsResult(500, "未登录", "");
        // 向下强制转型
        User user = (User) session.getAttribute("user");

        // 如果session中存在user，则是已登录成功
        if (user != null) {
            result.setCode(200);
            result.setMsg("当前为已登录状态");
            result.setData(user);
            // 控制台打印
            System.out.println(user.toString());
            return result;
        }
        return result;
    }

    /**
     * 账户退出登录
     */
    @RequestMapping(value = {"/executeLogout"}, method = RequestMethod.GET)
    @ResponseBody
    public ErsResult logout(Model model,
                            HttpSession session) {
        // 手动注销session，移除session中的用户信息
        session.removeAttribute("user");
        return new ErsResult(200, "success", "成功登出");
    }


}
