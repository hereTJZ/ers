package com.example.ers.biz;

import com.example.ers.entity.User;

public interface IAutoReplyBiz {

    User showSetting(int customerServiceId);//客服登陆打开此页面就会显示，之前的自动回复设置情况

}
