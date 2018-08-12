package com.example.demo.service.impl;

import com.example.demo.mapper.TopicMapper;
import com.example.demo.mapper.TopicRelationMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.Topic;
import com.example.demo.model.TopicRelation;
import com.example.demo.model.User;
import com.example.demo.service.TopicService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service(value = "topicService")
public class TopicServiceImpl implements TopicService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private TopicMapper topicMapper;

    @Autowired
    private TopicRelationMapper topicRelationMapper;

    @Override
    public int addTopic(Topic topic) {

        @NotNull
        User user = userMapper.selectByPrimaryKey(topic.getTopicUserPhone());
        user.setUserTopicsCount(user.getUserTopicsCount() + 1);
        userMapper.updateByPrimaryKeySelective(user);

        return topicMapper.insertSelective(topic);

    }

    @Override
    public int deleteTopic(int topic_id) {

        @NotNull
        Topic topic = topicMapper.selectByPrimaryKey(topic_id);
        @NotNull
        User user = userMapper.selectByPrimaryKey(topic.getTopicUserPhone());
        user.setUserTopicsCount(user.getUserTopicsCount() - 1);
        userMapper.updateByPrimaryKeySelective(user);

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

    @Override
    public List<Topic> getLimitUserCreatedTopics(String userPhone, Integer offset, Integer limit) {
        return topicMapper.selectLimitUserTopics(userPhone, offset, limit);
    }
    @Override
    public List<Topic> getLimitUserFavoriteTopics(String userPhone, Integer offset, Integer limit) {
        List<TopicRelation> topicRelationList = topicRelationMapper.selectFavoriteTopicRelationList(userPhone, offset, limit);

        List<Topic> topicList = new ArrayList<>();
        for (TopicRelation topicRelation: topicRelationList) {
            topicList.add(topicMapper.selectByPrimaryKey(topicRelation.getTopicId()));
        }
        return topicList;
    }


}
