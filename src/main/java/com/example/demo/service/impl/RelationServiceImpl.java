package com.example.demo.service.impl;

import com.example.demo.mapper.RelationMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.Relation;
import com.example.demo.model.RelationKey;
import com.example.demo.model.User;
import com.example.demo.service.RelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RelationServiceImpl implements RelationService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RelationMapper relationMapper;


    @Override
    public int block(String top_userPhone, String bottom_userPhone) {
        User top_user = userMapper.selectByPrimaryKey(top_userPhone);
        User bottom_user = userMapper.selectByPrimaryKey(bottom_userPhone);
        if (top_user != null && bottom_user != null) {

            RelationKey relationKey = new RelationKey();
            relationKey.setTopUser(top_userPhone);
            relationKey.setBottomUser(bottom_userPhone);

            Relation relation = relationMapper.selectByPrimaryKey(relationKey);
            if (relation != null) {
                relation.setBlockState(true);
                return relationMapper.updateByPrimaryKeySelective(relation);
            } else {
                relation = new Relation();
                relation.setTopUser(top_userPhone);
                relation.setBottomUser(bottom_userPhone);
                relation.setBlockState(true);
                return relationMapper.insertSelective(relation);
            }
        }

        return 0;
    }

    @Override
    public int unBlock(String top_userPhone, String bottom_userPhone) {
        User top_user = userMapper.selectByPrimaryKey(top_userPhone);
        User bottom_user = userMapper.selectByPrimaryKey(bottom_userPhone);
        if (top_user != null && bottom_user != null) {

            RelationKey relationKey = new RelationKey();
            relationKey.setTopUser(top_userPhone);
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
    public int follow(String top_userPhone, String bottom_userPhone) {
        User top_user = userMapper.selectByPrimaryKey(top_userPhone);
        User bottom_user = userMapper.selectByPrimaryKey(bottom_userPhone);
        if (top_user != null && bottom_user != null) {

            // 更新user
            top_user.setUserFollowingCount(top_user.getUserFollowingCount() + 1);
            bottom_user.setUserFollowersCount(bottom_user.getUserFollowersCount() + 1);
            userMapper.updateByPrimaryKeySelective(top_user);
            userMapper.updateByPrimaryKeySelective(bottom_user);


            // 更新relation
            RelationKey relationKey = new RelationKey();
            relationKey.setTopUser(top_userPhone);
            relationKey.setBottomUser(bottom_userPhone);

            Relation relation = relationMapper.selectByPrimaryKey(relationKey);

            if (relation != null) {
                relation.setFollowState(true);
                return relationMapper.updateByPrimaryKeySelective(relation);
            } else {
                relation = new Relation();
                relation.setTopUser(top_userPhone);
                relation.setBottomUser(bottom_userPhone);
                relation.setFollowState(true);
                return relationMapper.insertSelective(relation);
            }
        }
        return 0;
    }

    @Override
    public int unFollow(String top_userPhone, String bottom_userPhone) {
        User top_user = userMapper.selectByPrimaryKey(top_userPhone);
        User bottom_user = userMapper.selectByPrimaryKey(bottom_userPhone);
        if (top_user != null && bottom_user != null) {

            // 更新user
            top_user.setUserFollowingCount(top_user.getUserFollowingCount() - 1);
            bottom_user.setUserFollowersCount(bottom_user.getUserFollowersCount() - 1);
            userMapper.updateByPrimaryKeySelective(top_user);
            userMapper.updateByPrimaryKeySelective(bottom_user);

            RelationKey relationKey = new RelationKey();
            relationKey.setTopUser(top_userPhone);
            relationKey.setBottomUser(bottom_userPhone);

            Relation relation = relationMapper.selectByPrimaryKey(relationKey);

            if (relation != null) {
                relation.setFollowState(false);

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
    public List<User> getFollowingList(String top_userPhone, Integer offset, Integer limit) {
        List<Relation> relationList = relationMapper.selectFollowingsByTopUser(top_userPhone, offset, limit);
        List<User> userList = new ArrayList<>();
        for (Relation relation: relationList) {
            userList.add(userMapper.selectByPrimaryKey(relation.getBottomUser()));
        }
        return userList;
    }

    @Override
    public List<User> getFollowerList(String bottom_userPhone, Integer offset, Integer limit) {
        List<Relation> relationList = relationMapper.selectFollowingsByTopUser(bottom_userPhone, offset, limit);
        List<User> userList = new ArrayList<>();
        for (Relation relation: relationList) {
            userList.add(userMapper.selectByPrimaryKey(relation.getTopUser()));
        }
        return userList;
    }

    @Override
    public List<User> getBlockingList(String top_userPhone, Integer offset, Integer limit) {
        List<Relation> relationList = relationMapper.selectFollowingsByTopUser(top_userPhone, offset, limit);
        List<User> userList = new ArrayList<>();
        for (Relation relation: relationList) {
            userList.add(userMapper.selectByPrimaryKey(relation.getBottomUser()));
        }
        return userList;
    }
}
