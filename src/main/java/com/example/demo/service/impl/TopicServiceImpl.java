package com.example.demo.service.impl;

import com.example.demo.mapper.TopicMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.Topic;
import com.example.demo.model.User;
import com.example.demo.service.TopicService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service(value = "topicService")
public class TopicServiceImpl implements TopicService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private TopicMapper topicMapper;

    @Override
    public int addTopic(Topic topic) {
        User user = userMapper.selectByPrimaryKey(topic.getTopicUserPhone());
        if (user != null) {
            user.setUserTopicsCount(user.getUserTopicsCount() + 1);
            userMapper.updateByPrimaryKeySelective(user);
            return topicMapper.insertSelective(topic);
        }
        return 0;
    }

    @Override
    public int deleteTopic(int topic_id) {
        Topic topic = topicMapper.selectByPrimaryKey(topic_id);
        User user = userMapper.selectByPrimaryKey(topic.getTopicUserPhone());
        if (user != null) {
            user.setUserTopicsCount(user.getUserTopicsCount() - 1);
            userMapper.updateByPrimaryKeySelective(user);
        }
        return topicMapper.deleteByPrimaryKey(topic_id);
    }

    @Override
    public Topic selectTopic(int topic_id) {
        return topicMapper.selectByPrimaryKey(topic_id);
    }

    @Override
    public int updateTopic(Topic topic) {
        return topicMapper.updateByPrimaryKey(topic);
    }

    @Override
    public List<Topic> getLimitTopics(Integer offset, Integer limit) {
        return topicMapper.selectLimitTopics(offset, limit);
    }



}
