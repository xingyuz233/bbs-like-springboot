package com.example.demo.service.impl;

import com.example.demo.mapper.TopicMapper;
import com.example.demo.mapper.TopicReplyMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.Topic;
import com.example.demo.model.TopicReply;
import com.example.demo.model.User;
import com.example.demo.service.TopicRelationService;
import com.example.demo.service.TopicReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;

@Service(value = "topicReplyService")
public class TopicReplyServiceImpl implements TopicReplyService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private TopicMapper topicMapper;

    @Autowired
    private TopicReplyMapper topicReplyMapper;

    @Override
    public int addTopicReply(TopicReply topicReply) {
        @NotNull
        User user = userMapper.selectByPrimaryKey(topicReply.getReplyUserPhone());
        @NotNull
        Topic topic = topicMapper.selectByPrimaryKey(topicReply.getReplyTopicId());

        user.setUserRepliesCount(user.getUserRepliesCount() + 1);
        topic.setTopicReplies(topic.getTopicReplies() + 1);
        userMapper.updateByPrimaryKeySelective(user);
        topicMapper.updateByPrimaryKey(topic);

        return topicReplyMapper.insert(topicReply);
    }

    @Override
    public int deleteTopicReply(int reply_id) {
        @NotNull
        TopicReply topicReply = topicReplyMapper.selectByPrimaryKey(reply_id);
        @NotNull
        User user = userMapper.selectByPrimaryKey(topicReply.getReplyUserPhone());
        @NotNull
        Topic topic = topicMapper.selectByPrimaryKey(topicReply.getReplyTopicId());

        user.setUserRepliesCount(user.getUserRepliesCount() - 1);
        topic.setTopicReplies(topic.getTopicReplies() - 1);
        userMapper.updateByPrimaryKeySelective(user);
        topicMapper.updateByPrimaryKey(topic);

        return topicReplyMapper.deleteByPrimaryKey(reply_id);
    }

    @Override
    public TopicReply selectTopicReply(int reply_id) {
        return topicReplyMapper.selectByPrimaryKey(reply_id);
    }

    @Override
    public int updateTopicReply(TopicReply topicReply) {
        return topicReplyMapper.updateByPrimaryKey(topicReply);
    }

    @Override
    public List<TopicReply> getLimitTopicReplies(int topic_id, Integer offset, Integer limit) {
        return topicReplyMapper.selectLimitTopicReplies(topic_id, offset, limit);
    }
}
