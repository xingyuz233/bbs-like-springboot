package com.example.demo.mapper;

import com.example.demo.model.Relation;
import com.example.demo.model.RelationKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RelationMapper {
    int deleteByPrimaryKey(RelationKey key);

    int insert(Relation record);

    int insertSelective(Relation record);

    Relation selectByPrimaryKey(RelationKey key);

    int updateByPrimaryKeySelective(Relation record);

    int updateByPrimaryKey(Relation record);

    List<Relation> selectFollowingsByTopUser(@Param("topUser") String topUser, @Param("offset") Integer offset, @Param("limit") Integer limit);

    List<Relation> selectFollowersByBottomUser(@Param("bottomUser") String bottomUser, @Param("offset") Integer offset, @Param("limit") Integer limit);

    List<Relation> selectBlocksByTopUser(@Param("topUser") String topUser, @Param("offset") Integer offset, @Param("limit") Integer limit);

    List<Relation> selectFriendsByTopUser(@Param("topUser") String topUser, @Param("offset") Integer offset, @Param("limit") Integer limit);
}