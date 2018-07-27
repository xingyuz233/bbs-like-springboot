package com.example.demo.service.impl;

import com.example.demo.mapper.TopicMapper;
import com.example.demo.mapper.TopicRelationMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.Topic;
import com.example.demo.model.TopicRelation;
import com.example.demo.model.TopicRelationKey;
import com.example.demo.model.User;
import com.example.demo.service.TopicRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

@Service(value = "topicRelationService")
public class TopicRelationServiceImpl implements TopicRelationService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    TopicMapper topicMapper;

    @Autowired
    TopicRelationMapper topicRelationMapper;

    @Override
    public int favorite(String userPhone, int topicId) {
        @NotNull
        User user = userMapper.selectByPrimaryKey(userPhone);

        if (user != null) {

            user.setUserFavoritesCount(user.getUserFavoritesCount() + 1);
            userMapper.updateByPrimaryKeySelective(user);

            TopicRelationKey topicRelationKey = new TopicRelation();
            topicRelationKey.setUserPhone(userPhone);
            topicRelationKey.setTopicId(topicId);

            TopicRelation topicRelation = topicRelationMapper.selectByPrimaryKey(topicRelationKey);

            if (topicRelation != null) {
                topicRelation.setFavoriteState(true);
                return topicRelationMapper.updateByPrimaryKeySelective(topicRelation);
            } else {
                topicRelation = new TopicRelation();
                topicRelation.setUserPhone(userPhone);
                topicRelation.setTopicId(topicId);
                topicRelation.setFavoriteState(true);
                return topicRelationMapper.insertSelective(topicRelation);
            }
        }
        return 0;
    }

    @Override
    public int unFavorite(String userPhone, int topicId) {
        @NotNull
        User user = userMapper.selectByPrimaryKey(userPhone);

        if (user != null) {

            user.setUserFavoritesCount(user.getUserFavoritesCount() - 1);
            userMapper.updateByPrimaryKeySelective(user);

            TopicRelationKey topicRelationKey = new TopicRelation();
            topicRelationKey.setUserPhone(userPhone);
            topicRelationKey.setTopicId(topicId);

            TopicRelation topicRelation = topicRelationMapper.selectByPrimaryKey(topicRelationKey);

            if (topicRelation != null) {
                topicRelation.setFavoriteState(false);
                if (!topicRelation.getFollowState() && !topicRelation.getLikeState()) {
                    return  topicRelationMapper.deleteByPrimaryKey(topicRelationKey);
                } else {
                    return topicRelationMapper.updateByPrimaryKeySelective(topicRelation);
                }
            }
        }
        return 0;
    }

    @Override
    public int follow(String userPhone, int topicId) {

        TopicRelationKey topicRelationKey = new TopicRelation();
        topicRelationKey.setUserPhone(userPhone);
        topicRelationKey.setTopicId(topicId);

        TopicRelation topicRelation = topicRelationMapper.selectByPrimaryKey(topicRelationKey);

        if (topicRelation != null) {
            topicRelation.setFollowState(true);
            return topicRelationMapper.updateByPrimaryKeySelective(topicRelation);
        } else {
            topicRelation = new TopicRelation();
            topicRelation.setUserPhone(userPhone);
            topicRelation.setTopicId(topicId);
            topicRelation.setFollowState(true);
            return topicRelationMapper.insertSelective(topicRelation);
        }
    }

    @Override
    public int unFollow(String userPhone, int topicId) {
        TopicRelationKey topicRelationKey = new TopicRelation();
        topicRelationKey.setUserPhone(userPhone);
        topicRelationKey.setTopicId(topicId);

        TopicRelation topicRelation = topicRelationMapper.selectByPrimaryKey(topicRelationKey);

        if (topicRelation != null) {
            topicRelation.setFollowState(false);
            if (!topicRelation.getFavoriteState() && !topicRelation.getLikeState()) {
                return topicRelationMapper.deleteByPrimaryKey(topicRelationKey);
            } else {
                return topicRelationMapper.updateByPrimaryKeySelective(topicRelation);
            }
        }
        return 0;
    }

    @Override
    public int like(String userPhone, int topicId) {
        @NotNull
        Topic topic = topicMapper.selectByPrimaryKey(topicId);

        if (topic != null) {

            topic.setTopicLikes(topic.getTopicLikes() + 1);
            topicMapper.updateByPrimaryKeySelective(topic);

            TopicRelationKey topicRelationKey = new TopicRelation();
            topicRelationKey.setUserPhone(userPhone);
            topicRelationKey.setTopicId(topicId);

            TopicRelation topicRelation = topicRelationMapper.selectByPrimaryKey(topicRelationKey);

            if (topicRelation != null) {
                topicRelation.setLikeState(true);
                return topicRelationMapper.updateByPrimaryKeySelective(topicRelation);
            } else {
                topicRelation = new TopicRelation();
                topicRelation.setUserPhone(userPhone);
                topicRelation.setTopicId(topicId);
                topicRelation.setLikeState(true);
                return topicRelationMapper.insertSelective(topicRelation);
            }
        }
        return 0;
    }

    @Override
    public int unLike(String userPhone, int topicId) {
        @NotNull
        Topic topic = topicMapper.selectByPrimaryKey(topicId);

        if (topic != null) {

            topic.setTopicLikes(topic.getTopicLikes() - 1);
            topicMapper.updateByPrimaryKeySelective(topic);

            TopicRelationKey topicRelationKey = new TopicRelation();
            topicRelationKey.setUserPhone(userPhone);
            topicRelationKey.setTopicId(topicId);

            TopicRelation topicRelation = topicRelationMapper.selectByPrimaryKey(topicRelationKey);

            if (topicRelation != null) {
                topicRelation.setLikeState(false);
                if (!topicRelation.getFollowState() && !topicRelation.getFavoriteState()) {
                    return topicRelationMapper.deleteByPrimaryKey(topicRelationKey);
                } else {
                    return topicRelationMapper.updateByPrimaryKeySelective(topicRelation);
                }
            }
        }
        return 0;
    }
}
