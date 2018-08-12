package com.example.demo.mapper;

import com.example.demo.model.Topic;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TopicMapper {
    int deleteByPrimaryKey(Integer topicId);

    int insert(Topic record);

    int insertSelective(Topic record);

    Topic selectByPrimaryKey(Integer topicId);

    int updateByPrimaryKeySelective(Topic record);

    int updateByPrimaryKey(Topic record);

    List<Topic> selectLimitTopics(@Param("offset") Integer offset, @Param("limit") Integer limit);

    List<Topic> selectLimitUserTopics(@Param("userPhone") String userPhone, @Param("offset") Integer offset, @Param("limit") Integer limit);

}