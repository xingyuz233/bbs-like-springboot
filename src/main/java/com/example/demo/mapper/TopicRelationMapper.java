package com.example.demo.mapper;

import com.example.demo.model.TopicRelation;
import com.example.demo.model.TopicRelationKey;

public interface TopicRelationMapper {
    int deleteByPrimaryKey(TopicRelationKey key);

    int insert(TopicRelation record);

    int insertSelective(TopicRelation record);

    TopicRelation selectByPrimaryKey(TopicRelationKey key);

    int updateByPrimaryKeySelective(TopicRelation record);

    int updateByPrimaryKey(TopicRelation record);
}