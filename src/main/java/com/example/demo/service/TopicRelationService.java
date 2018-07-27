package com.example.demo.service;

public interface TopicRelationService {

    int favorite(String userPhone, int topicId);

    int unFavorite(String userPhone, int topicId);

    int follow(String userPhone, int topicId);

    int unFollow(String userPhone, int topicId);

    int like(String userPhone, int topicId);

    int unLike(String userPhone, int topicId);

}
