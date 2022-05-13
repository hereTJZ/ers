package com.example.ers.biz.impl;

import com.example.ers.biz.IExperimentBiz;
import com.example.ers.biz.IUserBiz;
import com.example.ers.dao.ExperimentMapper;
import com.example.ers.dao.UserMapper;
import com.example.ers.entity.Experiment;
import com.example.ers.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExperimentBizImpl implements IExperimentBiz {
    @Autowired
    private ExperimentMapper experimentMapper;
    @Autowired
    private IUserBiz userBiz;

    // 获取首页实验板块的链接
    @Override
    public List<Experiment> getFiveExperiments() {
        return experimentMapper.getFiveExperiments();
    }

    // 通过id获取实验
    @Override
    public Experiment getExperimentById(int id) {
        Experiment experiment = experimentMapper.getExperimentById(id);
        // 组长就是预约者
        User user = userBiz.findUserById(experiment.getUserId());
        experiment.setGroupLeader(user);
        return experiment;
    }
}
