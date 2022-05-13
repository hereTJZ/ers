package com.example.ers.biz.impl;

import com.example.ers.biz.IResourceBiz;
import com.example.ers.dao.ResourceMapper;
import com.example.ers.entity.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceBizImpl implements IResourceBiz {
    //依赖持久层接口
    @Autowired
    private ResourceMapper resourceMapper;

    // 获取首页资源板块的链接
    @Override
    public List<Resource> getFiveResources() {
        return resourceMapper.getFiveResources();
    }
}
