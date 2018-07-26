package com.example.demo.mapper;

import com.example.demo.model.Relation;
import com.example.demo.model.RelationKey;

public interface RelationMapper {
    int deleteByPrimaryKey(RelationKey key);

    int insert(Relation record);

    int insertSelective(Relation record);

    Relation selectByPrimaryKey(RelationKey key);

    int updateByPrimaryKeySelective(Relation record);

    int updateByPrimaryKey(Relation record);
}