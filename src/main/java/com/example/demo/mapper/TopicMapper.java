package com.example.demo.mapper;

import com.example.demo.model.Topic;
import net.sf.jsqlparser.statement.select.Top;

import java.util.List;

public interface TopicMapper {
    int deleteByPrimaryKey(Integer topicId);

    int insert(Topic record);

    int insertSelective(Topic record);

    Topic selectByPrimaryKey(Integer topicId);

    int updateByPrimaryKeySelective(Topic record);

    int updateByPrimaryKey(Topic record);

    List<Topic> selectAllTopics();

}