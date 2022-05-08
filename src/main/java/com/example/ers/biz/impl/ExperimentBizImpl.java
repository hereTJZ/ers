package com.example.ers.biz.impl;

import com.example.ers.biz.IExperimentBiz;
import com.example.ers.dao.ExperimentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExperimentBizImpl implements IExperimentBiz {
    @Autowired
    private ExperimentMapper experimentMapper;
}
