package com.example.demo.service;

import com.example.demo.model.News;

import java.util.List;

public interface NewsService {
    List<News> getLimitNews(Integer offset, Integer limit);
}
