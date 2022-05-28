package com.example.ers.controller;

import com.example.ers.biz.IUserBiz;
import com.example.ers.biz.impl.myHttpSessionListener;
import com.example.ers.entity.Notice;
import com.example.ers.entity.User;
import com.example.ers.utils.ErsResult;
import com.example.ers.utils.Util;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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
    public String getContactPage(HttpSession session, Model model) {
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
    public String getSettingPage(HttpSession session, Model model) {
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
    public String getResetPasswordPage(HttpSession session, Model model) {
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
     * 管理员用户管理界面--模糊搜索
     */
    @RequestMapping(value = {"/user"}, method = RequestMethod.GET)
    public String getUserPage(HttpSession session,
                              Model model,
                              @RequestParam(defaultValue = "1", value = "pageNum", required = false) int pageNum,
                              @RequestParam(defaultValue = "10", value = "pageSize", required = false) int pageSize,
                              @RequestParam(defaultValue = "2", value = "role", required = false) int role,
                              @RequestParam(defaultValue = "", value = "content", required = false) String content) {

        if (role < 2 || role > 4) {
            return "用户身份错误！";
        }

        // Object 类向下强制转型
        User user = (User) session.getAttribute("user");
        // 已登录的情况下
        if (user != null) {
            //导航栏用户信息（通用）
            model.addAttribute("user", user);
            model.addAttribute("identity", Util.userIdentity(user.getRole()));

            //分页信息
            PageInfo<User> pageInfo = userBiz.getUserPage(role, pageNum, pageSize, content);
            List<User> userList = pageInfo.getList();
            model.addAttribute("role", role);
            model.addAttribute("userList", userList);
            model.addAttribute("totalPages", pageInfo.getPages());
            model.addAttribute("currentPage", pageInfo.getPageNum());
            model.addAttribute("total", pageInfo.getTotal());
            //搜索区恢复
            model.addAttribute("searchContent", content);
        }
        return "user";
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

        // 首先核验当前客户端是否已经登录该账号
        if (userTemp != null) {
            if (userTemp.getPhone().equals(account) || userTemp.getEmail().equals(account)) {
                return new ErsResult(400, "error", "当前用户已经登录");
            }
            return new ErsResult(400, "error", "当前客户端已有用户登录！");
        }

        User user = userBiz.login(account, password);
        // 用户登录成功
        if (user != null) {
            // 监测此账号是否已经被登录
            if (myHttpSessionListener.isUserActive(user.getId())) {
                // 当前账号已在线
                return new ErsResult(500, "error", "当前账号已在线");
            }

//            // 设置格式
//            response.setHeader("Access-Control-Allow-Origin", "*");
//            response.setHeader("Access-Control-Allow-Methods", "POST");
//            response.setHeader("Access-Control-Allow-Headers","x-requested-with,content-type");
//            response.setContentType("text/html;charset=utf-8");
//            response.setCharacterEncoding("utf-8");
//
//            // 创建Cookie
//            Cookie cookie = new Cookie("userId", "userId");
//            // 有效期,秒为单位
//            cookie.setMaxAge(3600);
//            // 设置cookie
//            response.addCookie(cookie);
//            response.getWriter().print("cookie创建成功");

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
     * 登录状态核验--异步请求
     */
    @RequestMapping("/checkLogin")
    @ResponseBody
    public ErsResult checkLogin(HttpSession session) {
//        // 获取客户端cookie
//        request.setCharacterEncoding("utf-8");
//        Cookie[] cookies = request.getCookies();
//        if (cookies != null) {
//            for (Cookie c : cookies) {
//                System.out.println(c.getName() + "--->" + c.getValue());
//            }
//        }
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
        ErsResult result = new ErsResult(500, "error", "未登录");
        // 向下强制转型
        User user = (User) session.getAttribute("user");
        // 手动注销session，移除session中的用户信息
        if (user != null) {
            session.removeAttribute("user");
            session.invalidate();
            return new ErsResult(200, "success", "成功登出");
        }
        return result;
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

        // 判断账号是否已存在
        User userByPhone = userBiz.findUserByAccount(phone);
        User userByEmail = userBiz.findUserByAccount(email);

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

        // 设置用户角色
        int roleId = 4;
        // 获取当前java时间。    java.util.Date是所有时间类的父类，对应数据库应当转换成或直接使用Timestamp、java.sql.Date类
        Date date = new Date();
        // 用于格式化和解析日期
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Timestamp time = Timestamp.valueOf(dateFormat.format(date));

        User user = new User(phone, email, password, realName, roleId, time);
        userBiz.register(user);

        result.setData("注册时间：" + time + "😄");
        return result;
    }

    /**
     * 用户设置个人信息
     */
    @RequestMapping(value = {"/resetSelfInfo"}, method = RequestMethod.POST)
    @ResponseBody
    public ErsResult resetUserInfo(Model model,
                                   HttpSession session,
                                   @RequestParam("phone") String phone,
                                   @RequestParam("email") String email,
                                   @RequestParam(value = "gender", defaultValue = "", required = false) String gender,
                                   @RequestParam(value = "school", required = false) String school,
                                   @RequestParam(value = "faculty", required = false) String faculty,
                                   @RequestParam("realName") String realName,
                                   @RequestParam(value = "professional", required = false) String professional,
                                   @RequestParam(value = "grade", defaultValue = "", required = false) String grade,
                                   @RequestParam(value = "classNum", defaultValue = "", required = false) String classNum,
                                   @RequestParam(value = "subject", required = false) String subject) {

        ErsResult result = new ErsResult(500, "error", "未登录");

        // 向下强制转型
        User user = (User) session.getAttribute("user");
        if (user == null) return result;

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
        if (gender.equals("")) {
            result.setCode(400);
            result.setMsg("error");
            result.setData("当前选择性别");
            return result;
        }
        if ((!user.isSocial()) && school.equals("")) {
            result.setCode(400);
            result.setMsg("error");
            result.setData("当前未输入学校");
            return result;
        }
        if ((!user.isSocial()) && faculty.equals("")) {
            result.setCode(400);
            result.setMsg("error");
            result.setData("当前未输入学院");
            return result;
        }
        if (realName.equals("")) {
            result.setCode(400);
            result.setMsg("error");
            result.setData("当前未输入真实姓名");
            return result;
        }
        if (user.isStudent() && professional.equals("")) {
            result.setCode(400);
            result.setMsg("error");
            result.setData("当前未输入专业");
            return result;
        }
        if (user.isTeacher() && subject.equals("")) {
            result.setCode(400);
            result.setMsg("error");
            result.setData("当前未输入教学科目");
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
        if (!Util.isRealName(realName)) {
            result.setCode(500);
            result.setMsg("error");
            result.setData("请输入正确的中文名或英文名");
            return result;
        }
        if (user.isStudent() && (!Util.isNumber(grade) || Integer.parseInt(grade) <= 0)) {
            result.setCode(400);
            result.setMsg("error");
            result.setData("请输入正确的年级");
            return result;
        }
        if (user.isStudent() && (!Util.isNumber(classNum) || Integer.parseInt(classNum) <= 0)) {
            result.setCode(400);
            result.setMsg("error");
            result.setData("请输入正确的班级");
            return result;
        }


        // 更新用户信息，这里不需要非空验证
        user.setPhone(phone);
        user.setEmail(email);
        user.setGender(gender);
        user.setSchool(school);
        user.setFaculty(faculty);
        user.setRealName(realName);
        user.setProfessional(professional);
        if (!grade.equals("")) {
            user.setGrade(Integer.parseInt(grade));
        }
        if (!classNum.equals("")) {
            user.setClassNum(Integer.parseInt(classNum));
        }
        user.setSubject(subject);
        userBiz.resetUserInfo(user);
        session.setAttribute("user", user);

        result.setCode(200);
        result.setMsg("success");
        result.setData(user);
        return result;
    }

    /**
     * 用户重置密码
     */
    @RequestMapping(value = {"/resetSelfPassword"}, method = RequestMethod.POST)
    @ResponseBody
    public ErsResult resetUserPassword(Model model,
                                       HttpSession session,
                                       @RequestParam("oldPassword") String oldPassword,
                                       @RequestParam("newPassword") String newPassword) {

        // 向下强制转型
        User user = (User) session.getAttribute("user");

        // 已登录的情况下
        if (user != null) {
            User userTemp = userBiz.login(user.getPhone(), oldPassword);
            if (userTemp != null) {
                // 新密码合法性
                if (!Util.isVerifyPassword(newPassword)) {
                    return new ErsResult(400, "error", "请按要求设置新密码");
                }

                userTemp.setPassword(newPassword);
                userBiz.resetUserPassword(userTemp);
                return new ErsResult(200, "success", "密码修改成功！");
            }
            return new ErsResult(400, "error", "原密码错误，请重新输入！");
        }

        return new ErsResult(500, "forbidden", "当前用户未登录！");
    }

    /**
     * 用户修改自己的头像
     */
    @RequestMapping(value = {"/changeAvatar"}, method = RequestMethod.POST)
    @ResponseBody
    public ErsResult changeAvatar(Model model,
                                  HttpSession session,
                                  @RequestParam(value = "avatar", required = false) MultipartFile picture) {

        // 向下强制转型
        User user = (User) session.getAttribute("user");

        // 已登录的情况下
        if (user != null) {
            // 有照片的情况
            if (picture != null) {
                // 照片大小限制 5M
                if (picture.getSize() > 1024 * 1024 * 5) {
                    return new ErsResult(400, "error", "图片大小不能大于5M！");
                }
                // 获取图片后缀
                String suffix = picture.getOriginalFilename()
                        .substring(
                                picture.getOriginalFilename().lastIndexOf(".") + 1,
                                picture.getOriginalFilename().length()
                        );
                if (!"jpg,jpeg,gif,png".toUpperCase().contains(suffix.toUpperCase())) {
                    return new ErsResult(400, "error", "图片格式错误！请选择jpg,jpeg,gif,png格式的图片");
                }

                //通过UUID随机生成唯一文件名
                String filename = UUID.randomUUID().toString().replaceAll("-", "") + "." + suffix;

                /**
                 * 设置图片保存路径
                 */
//                // ① 项目运行时的根目录下创建用户上传的文件夹，项目重新部署时原数据会丢失
//                String savePath = request.getSession().getServletContext().getRealPath("\\upload\\img\\notice\\");
//                File savePathFile = new File(savePath);
//                //判断该路径是否存在
//                if (!savePathFile.exists()) {
//                    //若不存在该目录，则创建目录
//                    boolean isCreated = savePathFile.mkdirs();
//                    System.out.println(isCreated);
//                }

                // ② 获取jar包所在目录，在jar包所在目录下生成一个upload文件夹用来存储上传的图片
                ApplicationHome home = new ApplicationHome(getClass());
                File jarF = home.getSource();
                // 系统中的上传地址
                String uploadPath = jarF.getParentFile().toString() + "\\upload\\";
                // url中的相对地址
                String relativePath = "img\\avatar\\";
                File filePath = new File(uploadPath + relativePath);
                System.out.println(uploadPath);
                if (!filePath.exists()) {
                    boolean isCreated = filePath.mkdirs();
                    if (isCreated) {
                        System.out.println("头像图片文件夹创建成功！");
                    } else {
                        System.out.println("头像图片文件夹创建失败！");
                    }
                }

                // 保存图片
                try {
                    picture.transferTo(new File(uploadPath + relativePath + filename));
                } catch (Exception e) {
                    e.printStackTrace();
                    return new ErsResult(401, "error", e);
                }

                user.setImageAddress("upload\\" + relativePath + filename);
            }

            // 存入数据库
            int resultNum = userBiz.changeAvatar(user);
            if (resultNum == 1) {
                return new ErsResult(200, "success", "更新头像成功！");
            } else {
                return new ErsResult(400, "error", "存入数据库失败！");
            }
        }

        return new ErsResult(500, "forbidden", "当前用户未登录！");
    }

    /**
     * 管理员获取用户信息
     */
    @RequestMapping(value = {"/getUserById"}, method = RequestMethod.GET)
    @ResponseBody
    public ErsResult getUserById(Model model,
                                 HttpSession session,
                                 @RequestParam("id") int id) {

        // 向下强制转型
        User user = (User) session.getAttribute("user");

        // 已登录的情况下
        if (user != null) {

            // 管理员身份
            if (user.getRole() != 1) {
                return new ErsResult(500, "forbidden", "对不起，您不是管理员，没有权限！");
            }

            User userResult = userBiz.findUserById(id);

            return new ErsResult(200, "success", userResult);
        }
        return new ErsResult(500, "forbidden", "当前用户未登录！");
    }

    /**
     * 管理员修改用户信息
     */
    @RequestMapping(value = {"/resetUserInfo"}, method = RequestMethod.POST)
    @ResponseBody
    public ErsResult resetSelfInfo(Model model,
                                   HttpSession session,
                                   @RequestParam("id") String id,
                                   @RequestParam("role") String role,
                                   @RequestParam("phone") String phone,
                                   @RequestParam("email") String email,
                                   @RequestParam(value = "gender", defaultValue = "", required = false) String gender,
                                   @RequestParam(value = "school", required = false) String school,
                                   @RequestParam(value = "faculty", required = false) String faculty,
                                   @RequestParam("realName") String realName,
                                   @RequestParam(value = "professional", required = false) String professional,
                                   @RequestParam(value = "grade", defaultValue = "", required = false) String grade,
                                   @RequestParam(value = "classNum", defaultValue = "", required = false) String classNum,
                                   @RequestParam(value = "subject", required = false) String subject) {

        ErsResult result = new ErsResult(500, "forbidden", "未登录");

        // 向下强制转型
        User userOwn = (User) session.getAttribute("user");
        if (userOwn == null) return result;

        // 管理员身份
        if (userOwn.getRole() != 1) {
            return new ErsResult(500, "forbidden", "对不起，您不是管理员，没有权限！");
        }

        //输入信息非空
        if (id.equals("")) {
            result.setCode(400);
            result.setMsg("error");
            result.setData("当前id为空");
            return result;
        }
        if (role.equals("")) {
            result.setCode(400);
            result.setMsg("error");
            result.setData("当前身份为空");
            return result;
        }
        if (!Util.isNumber(role) || (1 > Integer.parseInt(role) || Integer.parseInt(role) > 4)) {
            result.setCode(400);
            result.setMsg("error");
            result.setData("请选择正确的身份码");
            return result;
        }
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
        if (gender.equals("")) {
            result.setCode(400);
            result.setMsg("error");
            result.setData("当前选择性别");
            return result;
        }
        if (!role.equals("4") && school.equals("")) {
            result.setCode(400);
            result.setMsg("error");
            result.setData("当前未输入学校");
            return result;
        }
        if (!role.equals("4") && faculty.equals("")) {
            result.setCode(400);
            result.setMsg("error");
            result.setData("当前未输入学院");
            return result;
        }
        if (realName.equals("")) {
            result.setCode(400);
            result.setMsg("error");
            result.setData("当前未输入真实姓名");
            return result;
        }
        if (role.equals("3") && professional.equals("")) {
            result.setCode(400);
            result.setMsg("error");
            result.setData("当前未输入专业");
            return result;
        }
        if (role.equals("2") && subject.equals("")) {
            result.setCode(400);
            result.setMsg("error");
            result.setData("当前未输入教学科目");
            return result;
        }

        //输入信息合法性判断
        if (!Util.isNumber(id)) {
            result.setCode(400);
            result.setMsg("error");
            result.setData("请输入正确的id");
            return result;
        }
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
        if (!Util.isRealName(realName)) {
            result.setCode(500);
            result.setMsg("error");
            result.setData("请输入正确的中文名或英文名");
            return result;
        }
        if (role.equals("3") && (!Util.isNumber(grade) || Integer.parseInt(grade) <= 0)) {
            result.setCode(400);
            result.setMsg("error");
            result.setData("请输入正确的年级");
            return result;
        }
        if (role.equals("3") && (!Util.isNumber(classNum) || Integer.parseInt(classNum) <= 0)) {
            result.setCode(400);
            result.setMsg("error");
            result.setData("请输入正确的班级");
            return result;
        }

        User user = new User();
        user.setId(Integer.parseInt(id));
        user.setRole(Integer.parseInt(role));
        user.setPhone(phone);
        user.setEmail(email);
        user.setGender(gender);
        user.setRealName(realName);
        // 教师
        if (role.equals("2")) {
            user.setSchool(school);
            user.setFaculty(faculty);
            user.setSubject(subject);
        }
        // 学生
        if (role.equals("3")) {
            user.setSchool(school);
            user.setFaculty(faculty);
            user.setProfessional(professional);
            if (!grade.equals("")) {
                user.setGrade(Integer.parseInt(grade));
            }
            if (!classNum.equals("")) {
                user.setClassNum(Integer.parseInt(classNum));
            }
        }

        userBiz.resetUserInfo(user);

        result.setCode(200);
        result.setMsg("success");
        result.setData(user);
        return result;
    }

    /**
     * 管理员添加（注册）用户
     */
    @RequestMapping(value = {"/addUser"}, method = RequestMethod.POST)
    @ResponseBody
    public ErsResult addUser(Model model,
                             HttpSession session,
                             @RequestParam("realName") String realName,
                             @RequestParam("role") String role,
                             @RequestParam("phone") String phone,
                             @RequestParam("email") String email,
                             @RequestParam("gender") String gender,
                             @RequestParam(defaultValue = "", value = "school", required = false) String school,
                             @RequestParam(defaultValue = "", value = "faculty", required = false) String faculty,
                             @RequestParam(defaultValue = "", value = "professional", required = false) String professional,
                             @RequestParam(defaultValue = "", value = "grade", required = false) String grade,
                             @RequestParam(defaultValue = "", value = "classNum", required = false) String classNum,
                             @RequestParam(defaultValue = "", value = "subject", required = false) String subject) {

        // 向下强制转型
        User userOwn = (User) session.getAttribute("user");
        if (userOwn == null) return new ErsResult(500, "forbidden", "未登录！");

        // 管理员身份
        if (userOwn.getRole() != 1) {
            return new ErsResult(500, "forbidden", "对不起，您不是管理员，没有权限！");
        }

        //输入信息非空
        if (phone.equals("")) {
            return new ErsResult(400, "error", "请输入手机号");
        }
        if (email.equals("")) {
            return new ErsResult(400, "error", "请输入邮箱");
        }
        if (realName.equals("")) {
            return new ErsResult(400, "error", "请输入真实姓名");
        }
        if (role.equals("")) {
            return new ErsResult(400, "error", "请选择用户身份");
        }
        if (gender.equals("")) {
            return new ErsResult(400, "error", "请选择用户性别");
        }

        //输入信息合法性判断
        if (!Util.isMobilePhone(phone)) {
            return new ErsResult(400, "error", "请输入正确的手机号");
        }
        if (!Util.isEmail(email)) {
            return new ErsResult(400, "error", "请输入正确的邮箱");
        }
        if (!Util.isRealName(realName)) {
            return new ErsResult(400, "error", "请输入正确的中文名或英文名");
        }
        if (!Util.isNumber(role) || (1 > Integer.parseInt(role) || Integer.parseInt(role) > 4)) {
            return new ErsResult(400, "error", "请选择正确的身份码");
        }

        // 学生
        if (role.equals("3") && (!Util.isNumber(grade) || Integer.parseInt(grade) <= 0)) {
            return new ErsResult(400, "error", "请输入正确的年级");
        }
        if (role.equals("3") && (!Util.isNumber(classNum) || Integer.parseInt(classNum) <= 0)) {
            return new ErsResult(400, "error", "请输入正确的班级");
        }

        User user = new User();
        user.setRole(Integer.parseInt(role));
        user.setPhone(phone);
        user.setEmail(email);
        user.setGender(gender);
        user.setRealName(realName);
        // 教师
        if (role.equals("2")) {
            user.setSchool(school);
            user.setFaculty(faculty);
            user.setSubject(subject);
        }
        // 学生
        if (role.equals("3")) {
            user.setSchool(school);
            user.setFaculty(faculty);
            user.setProfessional(professional);
            if (!grade.equals("")) {
                user.setGrade(Integer.parseInt(grade));
            }
            if (!classNum.equals("")) {
                user.setClassNum(Integer.parseInt(classNum));
            }
        }

        // 设置默认密码：123456
        user.setPassword("123456");

        // 获取当前java时间。    java.util.Date是所有时间类的父类，对应数据库应当转换成或直接使用Timestamp、java.sql.Date类
        Date date = new Date();
        // 用于格式化和解析日期
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Timestamp time = Timestamp.valueOf(dateFormat.format(date));
        user.setRegisterTime(time);

        int num = userBiz.register(user);
        if (num == 1) {
            return new ErsResult(200, "success", "添加新用户成功😄");
        } else {
            return new ErsResult(400, "error", "用户插入数据库失败");
        }
    }

    /**
     * 管理员删除用户
     */
    @RequestMapping(value = {"/deleteUserById"}, method = RequestMethod.GET)
    @ResponseBody
    public ErsResult deleteUserById(Model model,
                                    HttpSession session,
                                    @RequestParam("id") int id) {

        ErsResult result = new ErsResult(500, "forbidden", "未登录");

        // 向下强制转型
        User userOwn = (User) session.getAttribute("user");
        if (userOwn == null) return result;

        // 管理员身份
        if (userOwn.getRole() != 1) {
            return new ErsResult(500, "forbidden", "对不起，您不是管理员，没有权限！");
        }

        int num = userBiz.deleteUserById(id);

        if (num == 1) {
            return new ErsResult(200, "success", "删除用户成功");
        } else {
            return new ErsResult(400, "error", "数据库删除用户成功失败");
        }
    }


}








































































