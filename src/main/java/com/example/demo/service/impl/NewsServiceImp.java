package com.example.demo.service.impl;

import com.example.demo.mapper.NewsMapper;
import com.example.demo.model.News;
import com.example.demo.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "newsService")
public class NewsServiceImp implements NewsService {

    @Autowired
    private NewsMapper newsMapper;

    @Override
    public List<News> getLimitNews(Integer offset, Integer limit) {
        return newsMapper.selectLimitNewsList(offset, limit);
    }
}
