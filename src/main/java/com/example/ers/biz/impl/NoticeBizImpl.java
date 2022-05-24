package com.example.ers.biz.impl;

import com.example.ers.biz.INoticeBiz;
import com.example.ers.dao.NoticeMapper;
import com.example.ers.entity.Notice;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class NoticeBizImpl implements INoticeBiz {
    //依赖持久层接口
    @Autowired
    private NoticeMapper noticeMapper;

    // 获取首页公告板块的链接
    @Override
    public List<Notice> getFiveNews() {
        return noticeMapper.getFiveNews();
    }

    /**
     * 根据id查找公告内容
     * @param id
     * @return
     */
    @Override
    public Notice getNoticeById(int id) {
        return noticeMapper.getNoticeById(id);
    }

    /**
     * 根据 页数 和 页面大小 查找公告数据
     * @param pageNum 当前页码
     * @param pageSize 一页的大小，每页显示的数量
     * @param orderBy 是形如 "id desc" 的字符串，其中id为排序字段，desc为默认的降序，asc相反为升序
     * @return
     */
    @Override
    public PageInfo<Notice> getNewsPage(int pageNum, int pageSize) {
        // 如果要使用分页，先调用startPage方法
        PageHelper.startPage(pageNum, pageSize);
        // 根据 orderBy 排序
//        PageHelper.orderBy(orderBy);
        // PageHelper设置完成，执行查询所有数据
        List<Notice> notices = noticeMapper.getAllNotices();
        // 将所有的数据存放至分页的类中
        PageInfo<Notice> pageInfo = new PageInfo<>(notices, pageSize);
        return pageInfo;
    }

    /**
     * 模糊搜索公告，同时分页
     * @param content
     * @param beginTime
     * @param endTime
     * @return
     */
    @Override
    public PageInfo<Notice> fuzzySearchNotice(String content, Date beginTime, Date endTime, int pageNum, int pageSize) {
        // 如果要使用分页，先调用startPage方法
        PageHelper.startPage(pageNum, pageSize);

        // endTime查询包括当天的话，要在需求日期之上再加一天
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(endTime);
        calendar.add(Calendar.DAY_OF_YEAR,1);
        endTime = calendar.getTime();

        // PageHelper设置完成，执行查询所有数据
        List<Notice> notices = noticeMapper.fuzzySearchNotice(content, beginTime, endTime);
        // 将所有的数据存放至分页的类中
        PageInfo<Notice> pageInfo = new PageInfo<>(notices, pageSize);
        return pageInfo;
    }

    /**
     * 添加公告
     */
    @Override
    public int addNotice(Notice notice) {
        return noticeMapper.addNotice(notice);
    }


}
