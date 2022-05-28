package com.example.ers.biz.impl;

import com.example.ers.biz.IExperimentBiz;
import com.example.ers.biz.IUserBiz;
import com.example.ers.dao.ExperimentMapper;
import com.example.ers.dao.UserMapper;
import com.example.ers.entity.Experiment;
import com.example.ers.entity.Notice;
import com.example.ers.entity.User;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
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

    @Override
    public PageInfo<Experiment> fuzzySearchNotice(String content, Date beginTime, Date endTime, int pageNum, int pageSize) {
        // 如果要使用分页，先调用startPage方法
        PageHelper.startPage(pageNum, pageSize);

        // endTime查询包括当天的话，要在需求日期之上再加一天
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(endTime);
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        endTime = calendar.getTime();

        // PageHelper设置完成，执行查询所有数据
        List<Experiment> experiments = experimentMapper.fuzzySearchNotice(content, beginTime, endTime);
        // 预约者信息
        for (Experiment e : experiments) {
            // 组长就是预约者
            User user = userBiz.findUserById(e.getUserId());
            e.setGroupLeader(user);
        }
        // 将所有的数据存放至分页的类中
        PageInfo<Experiment> pageInfo = new PageInfo<>(experiments, pageSize);

        return pageInfo;
    }
}
