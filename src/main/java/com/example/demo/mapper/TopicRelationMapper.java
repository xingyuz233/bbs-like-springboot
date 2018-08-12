package com.example.demo.mapper;

import com.example.demo.model.TopicRelation;
import com.example.demo.model.TopicRelationKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TopicRelationMapper {
    int deleteByPrimaryKey(TopicRelationKey key);

    int insert(TopicRelation record);

    int insertSelective(TopicRelation record);

    TopicRelation selectByPrimaryKey(TopicRelationKey key);

    int updateByPrimaryKeySelective(TopicRelation record);

    int updateByPrimaryKey(TopicRelation record);

    List<TopicRelation> selectFavoriteTopicRelationList(@Param("userPhone") String userPhone, @Param("offset") Integer offset, @Param("limit") Integer limit);
}