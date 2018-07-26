package com.example.demo.service;

import com.example.demo.model.Relation;
import com.example.demo.model.User;

import java.util.List;

public interface RelationService {

    int block(String topic_userPhone, String bottom_userPhone);

    int unblock(String topic_userPhone, String bottom_userPhone);

    int follow(String topic_userPhone, String bottom_userPhone);

    int unfollow(String topic_userPhone, String bottom_userPhone);

    List<User> getFollowingList(String topic_userPhone);

    List<User> getFollowerList(String bottom_userPhone);




}
