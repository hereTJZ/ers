package com.example.ers.biz;

import com.example.ers.entity.Resource;

import java.util.List;

public interface IResourceBiz {
    // 获取首页资源板块的链接
    List<Resource> getFiveResources();
}
