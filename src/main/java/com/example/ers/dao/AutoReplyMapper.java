package com.example.ers.dao;

import com.example.ers.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AutoReplyMapper {

    User showSetting(int customerServiceId);//客服登陆打开此页面就会显示，之前的自动回复设置情况

}
