package com.example.ers.dao;

import com.example.ers.entity.Experiment;
import com.example.ers.entity.Notice;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

@Mapper
public interface ExperimentMapper {

    List<Experiment> getFiveExperiments();

    Experiment getExperimentById(int id);

    List<Experiment> fuzzySearchNotice(String content, Date beginTime, Date endTime);

}
