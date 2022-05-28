package com.example.ers.biz;

import com.example.ers.entity.Experiment;
import com.example.ers.entity.Notice;
import com.github.pagehelper.PageInfo;

import java.util.Date;
import java.util.List;

public interface IExperimentBiz {

    // 获取首页实验板块的链接
    List<Experiment> getFiveExperiments();

    //
    Experiment getExperimentById(int id);

    PageInfo<Experiment> fuzzySearchNotice(String content, Date beginTime, Date endTime, int pageNum, int pageSize);
}
