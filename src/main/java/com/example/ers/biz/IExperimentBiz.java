package com.example.ers.biz;

import com.example.ers.entity.Experiment;

import java.util.List;

public interface IExperimentBiz {

    // 获取首页实验板块的链接
    List<Experiment> getFiveExperiments();

    //
    Experiment getExperimentById(int id);
}
