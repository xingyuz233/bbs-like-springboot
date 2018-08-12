package com.example.demo.mapper;

import com.example.demo.model.TopicReply;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TopicReplyMapper {
    int deleteByPrimaryKey(Integer replyId);

    int insert(TopicReply record);

    int insertSelective(TopicReply record);

    TopicReply selectByPrimaryKey(Integer replyId);

    int updateByPrimaryKeySelective(TopicReply record);

    int updateByPrimaryKey(TopicReply record);

    List<TopicReply> selectLimitTopicReplies(@Param("topicId") int topicId, @Param("offset") Integer offset, @Param("limit") Integer limit);

}