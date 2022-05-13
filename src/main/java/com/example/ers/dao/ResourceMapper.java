package com.example.ers.dao;

import com.example.ers.entity.Resource;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ResourceMapper {
    // 获取首页资源板块的链接
    List<Resource> getFiveResources();
}
