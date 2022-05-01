package com.example.ers.dao;

import com.example.ers.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    User login(String account, String password);

}
