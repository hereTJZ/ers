package com.example.ers.controller;

import com.example.ers.biz.IUserBiz;
import com.example.ers.biz.impl.UserBiz;
import com.example.ers.entity.User;
import com.example.ers.utils.ErsResult;
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
    public String loginPage() {
        return "login";
    }

    /**
     * 注册界面
     */
    @RequestMapping(value = {"/regist"})
    public String registPage() {
        return "regist";
    }

    /**
     * 首页界面
     */
    @RequestMapping(value = {"/home"})
    public String homePage() {
        return "home";
    }

    /**
     * 登录核验
     */
    @RequestMapping(value = {"/loginCheck"}, method = RequestMethod.POST)
    @ResponseBody
    public ErsResult loginCheck(Model model,
                                HttpSession session,
                                @RequestParam("account") String account,
                                @RequestParam("password") String password) {

        session.setAttribute("account", account);
        model.addAttribute("account", account);
        model.addAttribute("password", password);

        User user = userBiz.login(account, password);
        if (user != null) {
            System.out.println(user.toString());
            return new ErsResult(200, "success", user);
        }else {
            return new ErsResult(404, "error", "账号或密码错误");
        }
    }
}
