package com.example.demo.model;

import java.util.Date;

public class News {
    private Integer newsId;

    private String newsTitle;

    private String newsAddress;

    private Date newsPublishTime;

    private Date newsModifyTime;

    private String newsUserPhone;

    private String newsLastReplyUserPhone;

    private Date newsLastReplyTime;

    private Integer newsReplyCount;

    public Integer getNewsId() {
        return newsId;
    }

    public void setNewsId(Integer newsId) {
        this.newsId = newsId;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle == null ? null : newsTitle.trim();
    }

    public String getNewsAddress() {
        return newsAddress;
    }

    public void setNewsAddress(String newsAddress) {
        this.newsAddress = newsAddress == null ? null : newsAddress.trim();
    }

    public Date getNewsPublishTime() {
        return newsPublishTime;
    }

    public void setNewsPublishTime(Date newsPublishTime) {
        this.newsPublishTime = newsPublishTime;
    }

    public Date getNewsModifyTime() {
        return newsModifyTime;
    }

    public void setNewsModifyTime(Date newsModifyTime) {
        this.newsModifyTime = newsModifyTime;
    }

    public String getNewsUserPhone() {
        return newsUserPhone;
    }

    public void setNewsUserPhone(String newsUserPhone) {
        this.newsUserPhone = newsUserPhone == null ? null : newsUserPhone.trim();
    }

    public String getNewsLastReplyUserPhone() {
        return newsLastReplyUserPhone;
    }

    public void setNewsLastReplyUserPhone(String newsLastReplyUserPhone) {
        this.newsLastReplyUserPhone = newsLastReplyUserPhone == null ? null : newsLastReplyUserPhone.trim();
    }

    public Date getNewsLastReplyTime() {
        return newsLastReplyTime;
    }

    public void setNewsLastReplyTime(Date newsLastReplyTime) {
        this.newsLastReplyTime = newsLastReplyTime;
    }

    public Integer getNewsReplyCount() {
        return newsReplyCount;
    }

    public void setNewsReplyCount(Integer newsReplyCount) {
        this.newsReplyCount = newsReplyCount;
    }
}