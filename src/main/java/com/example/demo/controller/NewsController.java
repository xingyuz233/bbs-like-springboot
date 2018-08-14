package com.example.demo.controller;


import com.example.demo.config.Constant;
import com.example.demo.controller.response.NewsDetail;
import com.example.demo.controller.response.UserBrief;
import com.example.demo.model.News;
import com.example.demo.model.User;
import com.example.demo.service.NewsService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/news")
public class NewsController {

    @Autowired
    NewsService newsService;

    @Autowired
    UserService userService;

    @GetMapping(value = "/list")
    public ResponseEntity<List<NewsDetail>> getTopicList(@RequestParam Map<String, String> body) {
        Integer offset,limit;

        try {
            offset = Integer.parseInt(body.get("offset"));
            limit = Integer.parseInt(body.get("limit"));
        } catch (NumberFormatException e) {
            offset = Constant.OFFSET_DEFAULT;
            limit = Constant.LIMIT_DEFAULT;
        }

        List<News> newsList = newsService.getLimitNews(offset, limit);
        List<NewsDetail> newsDetailList = new ArrayList<>();
        for (News news: newsList) {
            NewsDetail newsDetail = new NewsDetail(news);
            User user = userService.getUser(news.getNewsUserPhone());
            newsDetail.setUser(new UserBrief(user));
            newsDetailList.add(newsDetail);
        }
        return ResponseEntity.ok(newsDetailList);

    }

}
