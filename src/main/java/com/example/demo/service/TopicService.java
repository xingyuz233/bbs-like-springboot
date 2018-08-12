package com.example.demo.service;

import com.example.demo.model.Topic;

import java.util.List;

public interface TopicService {

    int addTopic(Topic topic);

    int deleteTopic(int topic_id);

    Topic selectTopic(int topic_id);

    int updateTopic(Topic topic);

    List<Topic> getLimitTopics(Integer offset, Integer limit);

    List<Topic> getLimitUserCreatedTopics(String userPhone, Integer offset, Integer limit);

    List<Topic> getLimitUserFavoriteTopics(String userPhone, Integer offset, Integer limit);

}
