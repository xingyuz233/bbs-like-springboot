package com.example.demo.service.impl;

import com.example.demo.mapper.TopicMapper;
import com.example.demo.model.Topic;
import com.example.demo.service.TopicService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service(value = "topicService")
public class TopicServiceImpl implements TopicService {

    @Autowired
    private TopicMapper topicMapper;

    @Override
    public int addTopic(Topic topic) {
        Date now = new Date();
        topic.setTopicPublishTime(now);
        topic.setTopicModifyTime(now);
        return topicMapper.insertSelective(topic);
    }

    @Override
    public int deleteTopic(int topic_id) {
        return topicMapper.deleteByPrimaryKey(topic_id);
    }

    @Override
    public Topic selectTopic(int topic_id) {
        return topicMapper.selectByPrimaryKey(topic_id);
    }

    @Override
    public List<Topic> selectAllTopics(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return topicMapper.selectAllTopics();
    }

}
