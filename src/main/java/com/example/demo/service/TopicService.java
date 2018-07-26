package com.example.demo.service;

import com.example.demo.model.Topic;

import java.util.List;

public interface TopicService {

    int addTopic(Topic topic);

    int deleteTopic(int topic_id);

    Topic selectTopic(int topic_id);

    List<Topic> selectAllTopics(int pageNum, int pageSize);

}
