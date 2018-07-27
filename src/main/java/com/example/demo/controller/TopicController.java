package com.example.demo.controller;


import com.example.demo.authorization.JwtTokenUtil;
import com.example.demo.config.Constant;
import com.example.demo.controller.response.State;
import com.example.demo.controller.response.TopicBrief;
import com.example.demo.controller.response.TopicDetail;
import com.example.demo.model.Topic;
import com.example.demo.service.TopicRelationService;
import com.example.demo.service.TopicService;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
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

        return ResponseEntity.ok(TopicBrief.getTopicBriefList(topicService.getLimitTopics(offset, limit)));

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

        if (topicService.addTopic(topic) == 1) {
            return ResponseEntity.ok(new TopicDetail(topic));
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
    public ResponseEntity<TopicDetail> getTopic(@PathVariable Integer id) {
        Topic topic = topicService.selectTopic(id);
        if (topic != null) {
            return ResponseEntity.ok(new TopicDetail(topic));
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
                return ResponseEntity.ok(new TopicDetail(topic));
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





}

