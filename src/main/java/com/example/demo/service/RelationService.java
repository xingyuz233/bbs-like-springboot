package com.example.demo.service;

import com.example.demo.model.Relation;
import com.example.demo.model.User;

import java.util.List;

public interface RelationService {

    int block(String top_userPhone, String bottom_userPhone);

    int unBlock(String top_userPhone, String bottom_userPhone);

    int follow(String top_userPhone, String bottom_userPhone);

    int unFollow(String top_userPhone, String bottom_userPhone);

    List<User> getFollowingList(String top_userPhone, Integer offset, Integer limit);

    List<User> getFollowerList(String bottom_userPhone, Integer offset, Integer limit);

    List<User> getBlockingList(String top_userPhone, Integer offset, Integer limit);

}
