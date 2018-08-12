package com.example.demo.service;

import com.example.demo.model.TopicReply;

import java.util.List;

public interface TopicReplyService {

    int addTopicReply(TopicReply topicReply);

    int deleteTopicReply(int reply_id);

    TopicReply selectTopicReply(int reply_id);

    int updateTopicReply(TopicReply topicReply);

    List<TopicReply> getLimitTopicReplies(int topic_id, Integer offset, Integer limit);


}
