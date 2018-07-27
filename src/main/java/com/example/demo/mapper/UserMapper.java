package com.example.demo.mapper;

import com.example.demo.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(String userPhone);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String userPhone);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    List<User> selectAllUsers();

    List<User> selectLimitUsers(@Param("offset") Integer offset, @Param("limit") Integer limit);

}