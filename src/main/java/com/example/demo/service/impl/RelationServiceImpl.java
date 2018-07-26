package com.example.demo.service.impl;

import com.example.demo.mapper.RelationMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.Relation;
import com.example.demo.model.RelationKey;
import com.example.demo.model.User;
import com.example.demo.service.RelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RelationServiceImpl implements RelationService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RelationMapper relationMapper;


    @Override
    public int block(String topic_userPhone, String bottom_userPhone) {
        User topic_user = userMapper.selectByPrimaryKey(topic_userPhone);
        User bottom_user = userMapper.selectByPrimaryKey(bottom_userPhone);
        if (topic_user != null && bottom_user != null) {

            RelationKey relationKey = new RelationKey();
            relationKey.setTopUser(topic_userPhone);
            relationKey.setBottomUser(bottom_userPhone);

            Relation relation = relationMapper.selectByPrimaryKey(relationKey);
            if (relation != null) {
                relation.setBlockState(true);
                return relationMapper.updateByPrimaryKeySelective(relation);
            } else {
                relation = new Relation();
                relation.setTopUser(topic_userPhone);
                relation.setBottomUser(bottom_userPhone);
                relation.setBlockState(true);
                return relationMapper.insertSelective(relation);
            }
        }

        return 0;
    }

    @Override
    public int unblock(String topic_userPhone, String bottom_userPhone) {
        User topic_user = userMapper.selectByPrimaryKey(topic_userPhone);
        User bottom_user = userMapper.selectByPrimaryKey(bottom_userPhone);
        if (topic_user != null && bottom_user != null) {

            RelationKey relationKey = new RelationKey();
            relationKey.setTopUser(topic_userPhone);
            relationKey.setBottomUser(bottom_userPhone);

            Relation relation = relationMapper.selectByPrimaryKey(relationKey);
            if (relation != null) {
                relation.setBlockState(false);

                if (!relation.getFollowState() && !relation.getFriendState()) {
                    return relationMapper.deleteByPrimaryKey(relationKey);
                } else {
                    return relationMapper.updateByPrimaryKeySelective(relation);
                }
            }
        }

        return 0;
    }

    @Override
    public int follow(String topic_userPhone, String bottom_userPhone) {

        return 0;
    }

    @Override
    public int unfollow(String topic_userPhone, String bottom_userPhone) {
        return 0;
    }

    @Override
    public List<User> getFollowingList(String topic_userPhone) {
        return null;
    }

    @Override
    public List<User> getFollowerList(String bottom_userPhone) {
        return null;
    }
}
