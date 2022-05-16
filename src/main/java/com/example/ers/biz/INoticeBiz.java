package com.example.ers.biz;

import com.example.ers.entity.Notice;
import com.github.pagehelper.PageInfo;

import java.util.Date;
import java.util.List;

public interface INoticeBiz {
    // 获取首页最新的5条公告
    List<Notice> getFiveNews();

    // 根据id查找公告内容
    Notice getNoticeById(int id);

    // 新闻公告页面：根据 页数、页面大小 查找数据
    PageInfo<Notice> getNewsPage(int pageNum, int pageSize);

    // 模糊搜索公告
    PageInfo<Notice> fuzzySearchNotice(String content, Date beginTime, Date endTime, int pageNum, int pageSize);
}
