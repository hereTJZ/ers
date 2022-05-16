package com.example.ers.biz.impl;

import com.example.ers.biz.IResourceBiz;
import com.example.ers.dao.ResourceMapper;
import com.example.ers.entity.Notice;
import com.example.ers.entity.Resource;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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

    // 获取资源页面
    @Override
    public PageInfo<Resource> getResourcePage(int pageNum, int pageSize) {
        // 如果要使用分页，先调用startPage方法
        PageHelper.startPage(pageNum, pageSize);
        // PageHelper设置完成，执行查询所有数据
        List<Resource> resourceList = resourceMapper.getAllResource();
        // 将所有的数据存放至分页的类中
        PageInfo<Resource> pageInfo = new PageInfo<>(resourceList, pageSize);
        return pageInfo;
    }
}
