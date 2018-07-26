package com.example.demo.controller;


import com.example.demo.authorization.JwtTokenUtil;
import com.example.demo.model.Topic;
import com.example.demo.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/topic")
public class TopicController {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    TopicService topicService;

    @PostMapping(value = "/add")
    public ResponseEntity<?> addTopic(@RequestHeader String tokenHeader, @RequestBody Topic topic) {
        String token = tokenHeader.substring(7);
        String phone = jwtTokenUtil.getUsernameFromToken(token);
        if (phone.equals(topic.getTopicUserPhone())) {
            if (topicService.addTopic(topic) == 1) {
                return ResponseEntity.ok("Success");
            } else {
                return ResponseEntity.badRequest().body("Failed");
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
        }
    }

    @DeleteMapping(value = "/delete/{topic_id}")
    public ResponseEntity<?> deleteTopic(@RequestHeader String tokenHeader, @PathVariable int topic_id) {
        String token = tokenHeader.substring(7);
        String phone = jwtTokenUtil.getUsernameFromToken(token);

        Topic topic = topicService.selectTopic(topic_id);

        if (topic != null && topic.getTopicUserPhone().equals(phone)) {
            if (topicService.deleteTopic(topic_id) == 1) {
                return ResponseEntity.ok("Success");
            } else {
                return ResponseEntity.badRequest().body("Failed");
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
        }
    }



}

