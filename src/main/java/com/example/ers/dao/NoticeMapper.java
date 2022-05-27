package com.example.ers.dao;

import com.example.ers.entity.Notice;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

@Mapper
public interface NoticeMapper {
    // 获取首页最新的5条公告
    List<Notice> getFiveNews();

    // 获取所有公告
    List<Notice> getAllNotices();

    // 根据id查找公告内容
    Notice getNoticeById(int id);

    // 模糊搜索公告
    List<Notice> fuzzySearchNotice(String content, Date beginTime, Date endTime);

    // 添加公告
    int addNotice(Notice notice);

    // 更新公告
    int resetNoticeInfo(Notice notice);

    // 删除公告
    int deleteNoticeById(int id);
}
