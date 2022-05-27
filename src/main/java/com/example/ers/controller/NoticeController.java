package com.example.ers.controller;

import com.example.ers.biz.INoticeBiz;
import com.example.ers.entity.Notice;
import com.example.ers.entity.User;
import com.example.ers.utils.ErsResult;
import com.example.ers.utils.Util;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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
                               HttpServletRequest request,
                               @RequestParam(defaultValue = "", value = "title", required = false) String title,
                               @RequestParam(defaultValue = "", value = "content", required = false) String content,
                               @RequestParam(value = "picture", required = false) MultipartFile picture) {

        // Object 类向下强制转型
        User user = (User) session.getAttribute("user");

        // 已登录的情况下
        if (user != null) {

            // 管理员身份
            if(user.getRole() != 1){
                return new ErsResult(500, "forbidden", "对不起，您不是管理员，没有权限！");
            }

            // 公告非空
            if (title.equals("")) {
                return new ErsResult(400, "error", "请输入公告标题！");
            }
            if (content.equals("")) {
                return new ErsResult(400, "error", "请输入公告内容！");
            }

            // 公告实体类
            Notice notice = new Notice();

            // 有照片的情况
            if (picture != null) {
                // 照片大小限制 5M
                if (picture.getSize() > 1024 * 1024 * 5) {
                    return new ErsResult(400, "error", "文件大小不能大于5M！");
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
                String relativePath = "img\\notice\\";
                File filePath = new File(uploadPath + relativePath);
                System.out.println(uploadPath);
                if (!filePath.exists()) {
                    boolean isCreated = filePath.mkdirs();
                    if (isCreated) {
                        System.out.println("公告图片文件夹创建成功！");
                    } else {
                        System.out.println("公告图片文件夹创建失败！");
                    }
                }

                // 保存图片
                try {
                    picture.transferTo(new File(uploadPath + relativePath + filename));
                } catch (Exception e) {
                    e.printStackTrace();
                    return new ErsResult(401, "error", e);
                }

                notice.setImageAddress("upload\\" + relativePath + filename);
            }

            // 封装notice
            notice.setTitle(title);
            notice.setContent(content);
            // 获取当前java时间
            Date date = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Timestamp time = Timestamp.valueOf(dateFormat.format(date));
            notice.setReleaseTime(time);

            // 存入数据库
            int resultNum = noticeBiz.addNotice(notice);
            if (resultNum == 1){
                return new ErsResult(200, "success", "发布成功！");
            } else {
                return new ErsResult(400, "error", "存入数据库失败！");
            }
        }
        return new ErsResult(500, "forbidden", "当前用户未登录！");
    }

    // 更新公告
    @RequestMapping(value = {"/refreshNotice"}, method = RequestMethod.POST)
    @ResponseBody
    public ErsResult refreshNotice(HttpSession session,
                               HttpServletRequest request,
                               @RequestParam(value = "id") int id,
                               @RequestParam(defaultValue = "", value = "title", required = false) String title,
                               @RequestParam(defaultValue = "", value = "content", required = false) String content,
                               @RequestParam(value = "picture", required = false) MultipartFile picture) {

        // Object 类向下强制转型
        User user = (User) session.getAttribute("user");

        // 已登录的情况下
        if (user != null) {

            // 管理员身份
            if(user.getRole() != 1){
                return new ErsResult(500, "forbidden", "对不起，您不是管理员，没有权限！");
            }

            // 公告非空
            if (title.equals("")) {
                return new ErsResult(400, "error", "请输入公告标题！");
            }
            if (content.equals("")) {
                return new ErsResult(400, "error", "请输入公告内容！");
            }

            // 公告实体类
            Notice notice = new Notice();

            // 有照片的情况
            if (picture != null) {
                // 照片大小限制 5M
                if (picture.getSize() > 1024 * 1024 * 5) {
                    return new ErsResult(400, "error", "文件大小不能大于5M！");
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
                String relativePath = "img\\notice\\";
                File filePath = new File(uploadPath + relativePath);
                System.out.println(uploadPath);
                if (!filePath.exists()) {
                    boolean isCreated = filePath.mkdirs();
                    if (isCreated) {
                        System.out.println("公告图片文件夹创建成功！");
                    } else {
                        System.out.println("公告图片文件夹创建失败！");
                    }
                }

                // 保存图片
                try {
                    picture.transferTo(new File(uploadPath + relativePath + filename));
                } catch (Exception e) {
                    e.printStackTrace();
                    return new ErsResult(401, "error", e);
                }

                notice.setImageAddress("upload\\" + relativePath + filename);
            }

            // 封装notice
            notice.setTitle(title);
            notice.setContent(content);
            // 获取当前java时间
            Date date = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Timestamp time = Timestamp.valueOf(dateFormat.format(date));
            notice.setReleaseTime(time);

            // 存入数据库
            int resultNum = noticeBiz.resetNoticeInfo(notice);
            if (resultNum == 1){
                return new ErsResult(200, "success", "修改成功！");
            } else {
                return new ErsResult(400, "error", "存入数据库失败！");
            }
        }
        return new ErsResult(500, "forbidden", "当前用户未登录！");
    }

    // 删除公告
    @RequestMapping(value = {"/deleteNotice"}, method = RequestMethod.GET)
    @ResponseBody
    public ErsResult deleteNotice(HttpSession session,
                                  @RequestParam(value = "id") int id) {
        // Object 类向下强制转型
        User user = (User) session.getAttribute("user");

        // 已登录的情况下
        if (user != null) {
            int num = noticeBiz.deleteNoticeById(id);
            if (num == 1) {
                return new ErsResult(200, "success", "删除公告成功！");
            } else {
                return new ErsResult(400, "error", "删除失败！");
            }
        }
        return new ErsResult(500, "error", "当前用户未登录！");
    }
}
