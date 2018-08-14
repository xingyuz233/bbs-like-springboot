package com.example.demo.controller;


import com.example.demo.authorization.JwtTokenUtil;
import com.example.demo.config.Constant;
import com.example.demo.controller.response.*;
import com.example.demo.model.Topic;
import com.example.demo.model.TopicRelation;
import com.example.demo.model.TopicReply;
import com.example.demo.model.User;
import com.example.demo.service.TopicRelationService;
import com.example.demo.service.TopicReplyService;
import com.example.demo.service.TopicService;
import com.example.demo.service.UserService;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NegativeOrZero;
import javax.validation.constraints.NotNull;
import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/topic")
public class TopicController {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    TopicService topicService;

    @Autowired
    TopicRelationService topicRelationService;

    @Autowired
    TopicReplyService topicReplyService;

    @Autowired
    UserService userService;

    /***
     * 获取topic列表
     * @param body
     * @return
     */
    @GetMapping(value = "/list")
    public ResponseEntity<List<TopicBrief>> getTopicList(@RequestParam Map<String, String> body) {
        Integer offset,limit;

        try {
            offset = Integer.parseInt(body.get("offset"));
            limit = Integer.parseInt(body.get("limit"));
        } catch (NumberFormatException e) {
            offset = Constant.OFFSET_DEFAULT;
            limit = Constant.LIMIT_DEFAULT;
        }

        List<Topic> topicList = topicService.getLimitTopics(offset, limit);
        List<TopicBrief> topicBriefList = new ArrayList<>();
        for (Topic topic: topicList) {
            TopicBrief topicBrief = new TopicBrief(topic);
            User user = userService.getUser(topic.getTopicUserPhone());
            topicBrief.setUser(new UserBrief(user));
            topicBriefList.add(topicBrief);
        }
        return ResponseEntity.ok(topicBriefList);

    }

    /***
     * 创建新的topic
     * @param Authorization
     * @param body
     * @return
     */
    @PostMapping(value = "/add")
    public ResponseEntity<TopicDetail> addTopic(@RequestHeader String Authorization, @RequestParam Map<String, String> body) {
        String token = Authorization.substring(7);
        @NotNull
        String phone = jwtTokenUtil.getUsernameFromToken(token);
        @NotNull
        String title = body.get("title");
        @NotNull
        String content = body.get("body");

        Topic topic = new Topic();
        topic.setTopicTitle(title);
        topic.setTopicContentMd(content);
        topic.setTopicUserPhone(phone);
        topic.setTopicPublishTime(new Date());
        topic.setTopicModifyTime(new Date());
        topic.setTopicLikes(0);
        topic.setTopicHits(0);
        topic.setTopicReplies(0);
        topic.setTopicDeleted(false);
        topic.setTopicExcellent(false);


        if (topicService.addTopic(topic) == 1) {
            topic.setTopicId(-1);
            User user = userService.getUser(phone);
            TopicDetail topicDetail = new TopicDetail(topic);
            topicDetail.setUser(new UserBrief(user));
            return ResponseEntity.ok(topicDetail);
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }

    /***
     * 获取topic详情
     * @param id
     * @return
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<TopicDetail> getTopic(@RequestHeader String Authorization, @PathVariable Integer id) {
        Topic topic = topicService.selectTopic(id);
        if (topic != null) {
            User user = userService.getUser(topic.getTopicUserPhone());
            TopicDetail topicDetail = new TopicDetail(topic);
            topicDetail.setUser(new UserBrief(user));

            if (Authorization != null && Authorization.length() > 7) {
                String token = Authorization.substring(7);
                String phone = jwtTokenUtil.getUsernameFromToken(token);
                TopicRelation topicRelation = topicRelationService.selectTopicRelation(phone,id);

                if (topicRelation != null) {
                    if (topicRelation.getFavoriteState()) {
                        topicDetail.setFavorited(true);
                    }

                    if (topicRelation.getLikeState()) {
                        topicDetail.setLiked(true);
                    }
                }
            }

            return ResponseEntity.ok(topicDetail);
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }

    /***
     * 更新话题
     * @param id
     * @param body
     * @return
     */
    @PostMapping(value = "/{id}")
    public ResponseEntity<TopicDetail> updateTopic(@RequestHeader String Authorization, @PathVariable Integer id, @RequestParam Map<String, String> body) {
        @NotNull
        String token = Authorization.substring(7);
        @NotNull
        String phone = jwtTokenUtil.getUsernameFromToken(token);
        @NotNull
        String title = body.get("title");
        @NotNull
        String content = body.get("body");

        Topic topic = topicService.selectTopic(id);
        if (topic != null && topic.getTopicUserPhone().equals(phone)) {
            topic.setTopicTitle(title);
            topic.setTopicContentMd(content);
            topic.setTopicModifyTime(new Date());
            if (topicService.updateTopic(topic) == 1) {
                User user = userService.getUser(topic.getTopicUserPhone());
                TopicDetail topicDetail = new TopicDetail(topic);
                topicDetail.setUser(new UserBrief(user));
                return ResponseEntity.ok(topicDetail);
            }
        }
        return ResponseEntity.badRequest().body(null);
    }

    /***
     * 删除话题
     * @param Authorization
     * @param topic_id
     * @return
     */
    @DeleteMapping(value = "/{topic_id}")
    public ResponseEntity<State> deleteTopic(@RequestHeader String Authorization, @PathVariable int topic_id) {
        @NotNull
        String token = Authorization.substring(7);
        @NotNull
        String phone = jwtTokenUtil.getUsernameFromToken(token);

        Topic topic = topicService.selectTopic(topic_id);
        if (topic != null && topic.getTopicUserPhone().equals(phone)) {
            if (topicService.deleteTopic(topic_id) == 1) {
                return ResponseEntity.ok(new State(1));
            }
        }
        return ResponseEntity.badRequest().body(new State(0));
    }


    @PostMapping(value = "/favorite/{topic_id}")
    public ResponseEntity<State> favoriteTopic(@RequestHeader String Authorization, @PathVariable int topic_id) {
        @NotNull
        String token = Authorization.substring(7);
        @NotNull
        String phone = jwtTokenUtil.getUsernameFromToken(token);

        if (topicRelationService.favorite(phone, topic_id) == 1) {
            return ResponseEntity.ok(new State(1));
        } else {
            return ResponseEntity.badRequest().body(new State(0));
        }
    }

    @PostMapping(value = "/unFavorite/{topic_id}")
    public ResponseEntity<State> unFavoriteTopic(@RequestHeader String Authorization, @PathVariable int topic_id) {
        @NotNull
        String token = Authorization.substring(7);
        @NotNull
        String phone = jwtTokenUtil.getUsernameFromToken(token);

        if (topicRelationService.unFavorite(phone, topic_id) == 1) {
            return ResponseEntity.ok(new State(1));
        } else {
            return ResponseEntity.badRequest().body(new State(0));
        }
    }

    @PostMapping(value = "/follow/{topic_id}")
    public ResponseEntity<State> followTopic(@RequestHeader String Authorization, @PathVariable int topic_id) {
        @NotNull
        String token = Authorization.substring(7);
        @NotNull
        String phone = jwtTokenUtil.getUsernameFromToken(token);

        if (topicRelationService.follow(phone, topic_id) == 1) {
            return ResponseEntity.ok(new State(1));
        } else {
            return ResponseEntity.badRequest().body(new State(0));
        }
    }

    @PostMapping(value = "/unFollow/{topic_id}")
    public ResponseEntity<State> unFollowTopic(@RequestHeader String Authorization, @PathVariable int topic_id) {
        @NotNull
        String token = Authorization.substring(7);
        @NotNull
        String phone = jwtTokenUtil.getUsernameFromToken(token);

        if (topicRelationService.unFollow(phone, topic_id) == 1) {
            return ResponseEntity.ok(new State(1));
        } else {
            return ResponseEntity.badRequest().body(new State(0));
        }
    }


    //reply部分
    @GetMapping(value = "/replyList/{topic_id}")
    public ResponseEntity<List<TopicReplyDetail>> getTopicReplyList(@PathVariable int topic_id, @RequestParam Map<String, String> body) {
        Integer offset,limit;
        try {
            offset = Integer.parseInt(body.get("offset"));
            limit = Integer.parseInt(body.get("limit"));
        } catch (NumberFormatException e) {
            offset = Constant.OFFSET_DEFAULT;
            limit = Constant.LIMIT_DEFAULT;
        }

        List<TopicReply> topicReplyList = topicReplyService.getLimitTopicReplies(topic_id, offset, limit);
        List<TopicReplyDetail> topicReplyDetailList = new ArrayList<>();
        for (TopicReply topicReply: topicReplyList) {
            TopicReplyDetail topicReplyDetail = new TopicReplyDetail(topicReply);

            User user = userService.getUser(topicReply.getReplyUserPhone());
            topicReplyDetail.setUser(new UserBrief(user));

            topicReplyDetailList.add(topicReplyDetail);
        }
        return ResponseEntity.ok(topicReplyDetailList);
    }

    @GetMapping(value = "/reply/{id}")
    public ResponseEntity<TopicReplyDetail> getTopicReply(@PathVariable int id) {
        @NotNull
        TopicReply topicReply = topicReplyService.selectTopicReply(id);
        @NotNull
        User user = userService.getUser(topicReply.getReplyUserPhone());

        TopicReplyDetail topicReplyDetail = new TopicReplyDetail(topicReply);
        topicReplyDetail.setUser(new UserBrief(user));

        return ResponseEntity.ok(topicReplyDetail);
    }


    @PostMapping(value = "/replyAdd/{topic_id}")
    public ResponseEntity<TopicReplyDetail> createTopicReply(@RequestHeader String Authorization, @PathVariable int topic_id, @RequestParam Map<String, String> body) {
        @NotNull
        String token = Authorization.substring(7);
        @NotNull
        String phone = jwtTokenUtil.getUsernameFromToken(token);
        @NotNull
        String content = body.get("body");

        TopicReply topicReply = new TopicReply();
        topicReply.setReplyContent(content);
        topicReply.setReplyDeleted(false);
        topicReply.setReplyLikesCount(0);
        topicReply.setReplyPublishTime(new Date());
        topicReply.setReplyModifyTime(new Date());
        topicReply.setReplyTopicId(topic_id);
        topicReply.setReplyUserPhone(phone);

        if (topicReplyService.addTopicReply(topicReply) == 1) {
            topicReply.setReplyId(-1);
            @NotNull
            User user = userService.getUser(topicReply.getReplyUserPhone());
            UserBrief userBrief = new UserBrief(user);
            TopicReplyDetail topicReplyDetail = new TopicReplyDetail(topicReply);
            topicReplyDetail.setUser(userBrief);
            return ResponseEntity.ok(topicReplyDetail);

        } else {
            return ResponseEntity.badRequest().body(null);

        }



    }

}

