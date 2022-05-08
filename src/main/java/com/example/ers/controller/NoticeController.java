package com.example.ers.controller;

import com.example.ers.biz.INoticeBiz;
import com.example.ers.biz.IUserBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class NoticeController {
    //依赖业务层接口对象
    @Autowired
    private INoticeBiz noticeBiz;

}
