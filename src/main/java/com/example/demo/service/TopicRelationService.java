package com.example.demo.service;

import com.example.demo.model.TopicRelation;

import java.util.List;

public interface TopicRelationService {

    int favorite(String userPhone, int topicId);

    int unFavorite(String userPhone, int topicId);

    int follow(String userPhone, int topicId);

    int unFollow(String userPhone, int topicId);

    int like(String userPhone, int topicId);

    int unLike(String userPhone, int topicId);

    TopicRelation selectTopicRelation(String userPhone, int topicId);


}
