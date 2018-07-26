package com.example.demo.model;

import java.util.Date;

public class Topic {
    private Integer topicId;

    private String topicTitle;

    private String topicContent;

    private Date topicModifyTime;

    private Date topicPublishTime;

    private String topicUserPhone;

    public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

    public String getTopicTitle() {
        return topicTitle;
    }

    public void setTopicTitle(String topicTitle) {
        this.topicTitle = topicTitle == null ? null : topicTitle.trim();
    }

    public String getTopicContent() {
        return topicContent;
    }

    public void setTopicContent(String topicContent) {
        this.topicContent = topicContent == null ? null : topicContent.trim();
    }

    public Date getTopicModifyTime() {
        return topicModifyTime;
    }

    public void setTopicModifyTime(Date topicModifyTime) {
        this.topicModifyTime = topicModifyTime;
    }

    public Date getTopicPublishTime() {
        return topicPublishTime;
    }

    public void setTopicPublishTime(Date topicPublishTime) {
        this.topicPublishTime = topicPublishTime;
    }

    public String getTopicUserPhone() {
        return topicUserPhone;
    }

    public void setTopicUserPhone(String topicUserPhone) {
        this.topicUserPhone = topicUserPhone == null ? null : topicUserPhone.trim();
    }
}