package com.example.ers.biz.impl;

import com.example.ers.biz.IResourceBiz;
import com.example.ers.dao.ResourceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResourceBizImpl implements IResourceBiz {
    //依赖持久层接口
    @Autowired
    private ResourceMapper resourceMapper;
}
