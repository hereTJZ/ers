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
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

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
     * 联系我们页面
     */
    @RequestMapping(value = {"/contact"}, method = RequestMethod.GET)
    public String getContactPage(HttpSession session, Model model){
        // Object 类向下强制转型
        User user = (User) session.getAttribute("user");
        // 已登录的情况下
        if (user != null) {
            //导航栏用户信息（通用）
            model.addAttribute("user", user);
            model.addAttribute("identity", Util.userIdentity(user.getRole()));

        }
        return "contact";
    }

    /**
     * 设置界面
     */
    @RequestMapping(value = {"/setting"}, method = RequestMethod.GET)
    public String getSettingPage(HttpSession session, Model model){
        // Object 类向下强制转型
        User user = (User) session.getAttribute("user");
        // 已登录的情况下
        if (user != null) {
            //导航栏用户信息（通用）
            model.addAttribute("user", user);
            model.addAttribute("identity", Util.userIdentity(user.getRole()));

        }
        return "setting";
    }

    /**
     * 修改密码界面
     */
    @RequestMapping(value = {"/resetPassword"}, method = RequestMethod.GET)
    public String getResetPasswordPage(HttpSession session, Model model){
        // Object 类向下强制转型
        User user = (User) session.getAttribute("user");
        // 已登录的情况下
        if (user != null) {
            //导航栏用户信息（通用）
            model.addAttribute("user", user);
            model.addAttribute("identity", Util.userIdentity(user.getRole()));

        }
        return "resetPassword";
    }


    /**
     * 登录账户密码验证--异步请求
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
            if (userTemp.getPhone().equals(account) || userTemp.getEmail().equals(account)) {
                return new ErsResult(400, "error", "当前用户已经登录");
            }
            return new ErsResult(400, "error", "当前已有用户登录！");
        }
        User user = userBiz.login(account, password);
        // 用户登录成功
        if (user != null) {
            // 打印登录用户
//            System.out.println(user.toString());
            // 将用户信息放入session
            session.setAttribute("user", user);
            return new ErsResult(200, "success", user);

        } else {
            // 用户登录失败
            return new ErsResult(500, "error", "账号或密码错误");
        }
    }

    /**
     * 手动注册账户发送邮箱验证码--异步请求
     */
    @RequestMapping(value = {"/sendEmail"}, method = RequestMethod.POST)
    @ResponseBody
    public ErsResult checkEmail(Model model,
                                HttpSession session,
                                @RequestParam("phone") String phone,
                                @RequestParam("email") String email) {
        // 响应消息
        ErsResult result = new ErsResult();

        //输入手机号和邮箱非空
        if (phone.equals("")) {
            result.setCode(400);
            result.setMsg("error");
            result.setData("当前未输入手机号");
            return result;
        }
        if (email.equals("")) {
            result.setCode(400);
            result.setMsg("error");
            result.setData("当前未输入邮箱");
            return result;
        }

        //手机号和邮箱合法性判断
        if (!Util.isMobilePhone(phone)) {
            result.setCode(500);
            result.setMsg("error");
            result.setData("请输入正确的手机号");
            return result;
        }
        if (!Util.isEmail(email)) {
            result.setCode(500);
            result.setMsg("error");
            result.setData("请输入正确的邮箱");
            return result;
        }

        // 判断账号是否已存在
        User userByPhone = userBiz.findUserByAccount(phone);
        User userByEmail = userBiz.findUserByAccount(email);
//        System.out.println(userByPhone);
//        System.out.println(userByEmail);
        // 当前账号已存在时
        if (userByPhone != null) {
            result.setCode(500);
            result.setMsg("error");
            result.setData("当前手机号已存在用户！");
            return result;
        }
        if (userByEmail != null) {
            result.setCode(500);
            result.setMsg("error");
            result.setData("当前邮箱已存在用户！");
            return result;
        }

        // 随机生成6位数验证码
        String s = Util.createCode(6);
        // 验证码存入当前session
        session.setAttribute("verifyCode", s);
        // 核验成功，发送短信
        userBiz.sendEmail(email, s);
        result.setCode(200);
        result.setMsg("success");
        result.setData("已发送至邮箱：" + email);
        return result;
    }

    /**
     * 手动注册账户验证--异步请求
     */
    @RequestMapping(value = {"/executeRegister"}, method = RequestMethod.POST)
    @ResponseBody
    public ErsResult registerCheck(Model model,
                                   HttpSession session,
                                   @RequestParam("phone") String phone,
                                   @RequestParam("email") String email,
                                   @RequestParam("verifyCode") String verifyCode,
                                   @RequestParam("password") String password,
                                   @RequestParam("realName") String realName) {
//        System.out.println(phone+"\n" +email+"\n" +verifyCode+"\n" +password+"\n" +realName);

        ErsResult result = new ErsResult(200, "success", "");

        //输入信息非空
        if (phone.equals("")) {
            result.setCode(400);
            result.setMsg("error");
            result.setData("当前未输入手机号");
            return result;
        }
        if (email.equals("")) {
            result.setCode(400);
            result.setMsg("error");
            result.setData("当前未输入邮箱");
            return result;
        }
        if (verifyCode.equals("")) {
            result.setCode(400);
            result.setMsg("error");
            result.setData("当前未输入验证码");
            return result;
        }
        if (password.equals("")) {
            result.setCode(400);
            result.setMsg("error");
            result.setData("当前未输入密码");
            return result;
        }
        if (realName.equals("")) {
            result.setCode(400);
            result.setMsg("error");
            result.setData("当前未输入真实姓名");
            return result;
        }

        //输入信息合法性判断
        if (!Util.isMobilePhone(phone)) {
            result.setCode(500);
            result.setMsg("error");
            result.setData("请输入正确的手机号");
            return result;
        }
        if (!Util.isEmail(email)) {
            result.setCode(500);
            result.setMsg("error");
            result.setData("请输入正确的邮箱");
            return result;
        }
        if (!Util.isVerifyPassword(password)) {
            result.setCode(500);
            result.setMsg("error");
            result.setData("正确的密码为6~20位，可以包含大小写字母、数字、下划线以及特殊字符!@#$%^&*-");
            return result;
        }
        if (!Util.isRealName(realName)) {
            result.setCode(500);
            result.setMsg("error");
            result.setData("请输入正确的中文名或英文名");
            return result;
        }

        // 验证码是否正确
        if (!verifyCode.equals(session.getAttribute("verifyCode"))) {
            result.setCode(400);
            result.setMsg("error");
            result.setData("验证码错误！");
            return result;
        }

        // 设置用户信息
        int roleId = 4;
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Timestamp time = Timestamp.valueOf(dateFormat.format(date));//获取当前时间
        userBiz.register(phone, email, password, realName, roleId, time);
        result.setData("注册时间：" + time + "😄");
        return result;
    }

    /**
     * 登录状态核验--异步请求
     */
    @RequestMapping("/checkLogin")
    @ResponseBody
    public ErsResult checkLogin(HttpSession session) {
//        System.out.println("执行登录状态核验~");
        ErsResult result = new ErsResult(500, "error", "未登录");
        // 向下强制转型
        User user = (User) session.getAttribute("user");

        // 如果session中存在user，则是已登录成功
        if (user != null) {
            result.setCode(200);
            result.setMsg("当前为已登录状态");
            result.setData(user);
            // 控制台打印
//            System.out.println(user.toString());
            return result;
        }
        return result;
    }

    /**
     * 账户退出登录--异步请求
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
