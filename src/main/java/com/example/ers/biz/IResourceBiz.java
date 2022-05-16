package com.example.ers.biz;

import com.example.ers.entity.Notice;
import com.example.ers.entity.Resource;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IResourceBiz {

    // 获取首页资源板块的链接
    List<Resource> getFiveResources();

    // 获取资源页面
    PageInfo<Resource> getResourcePage(int pageNum, int pageSize);
}
