package com.example.ers.dao;

import com.example.ers.entity.Experiment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ExperimentMapper {

    List<Experiment> getFiveExperiments();

    Experiment getExperimentById(int id);

}
