package com.example.ers.biz.impl;

import com.example.ers.biz.INoticeBiz;
import com.example.ers.dao.NoticeMapper;
import com.example.ers.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoticeBizImpl implements INoticeBiz {
    //依赖持久层接口
    @Autowired
    private NoticeMapper noticeMapper;
}
