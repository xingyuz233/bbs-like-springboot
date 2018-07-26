package com.example.demo.mapper;

import com.example.demo.model.User;

public interface UserMapper {
    int deleteByPrimaryKey(String userPhone);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String userPhone);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}