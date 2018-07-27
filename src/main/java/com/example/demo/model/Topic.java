package com.example.demo.model;

import java.util.Date;

public class Topic {
    private Integer topicId;

    private String topicTitle;

    private String topicContentMd;

    private String topicContentHtml;

    private Date topicPublishTime;

    private Date topicModifyTime;

    private Date topicLastReplyTime;

    private Integer topicLikes;

    private Integer topicHits;

    private Integer topicReplies;

    private Boolean topicDeleted;

    private Boolean topicExcellent;

    private String topicUserPhone;

    private String topicLastReplyUserPhone;

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

    public String getTopicContentMd() {
        return topicContentMd;
    }

    public void setTopicContentMd(String topicContentMd) {
        this.topicContentMd = topicContentMd == null ? null : topicContentMd.trim();
    }

    public String getTopicContentHtml() {
        return topicContentHtml;
    }

    public void setTopicContentHtml(String topicContentHtml) {
        this.topicContentHtml = topicContentHtml == null ? null : topicContentHtml.trim();
    }

    public Date getTopicPublishTime() {
        return topicPublishTime;
    }

    public void setTopicPublishTime(Date topicPublishTime) {
        this.topicPublishTime = topicPublishTime;
    }

    public Date getTopicModifyTime() {
        return topicModifyTime;
    }

    public void setTopicModifyTime(Date topicModifyTime) {
        this.topicModifyTime = topicModifyTime;
    }

    public Date getTopicLastReplyTime() {
        return topicLastReplyTime;
    }

    public void setTopicLastReplyTime(Date topicLastReplyTime) {
        this.topicLastReplyTime = topicLastReplyTime;
    }

    public Integer getTopicLikes() {
        return topicLikes;
    }

    public void setTopicLikes(Integer topicLikes) {
        this.topicLikes = topicLikes;
    }

    public Integer getTopicHits() {
        return topicHits;
    }

    public void setTopicHits(Integer topicHits) {
        this.topicHits = topicHits;
    }

    public Integer getTopicReplies() {
        return topicReplies;
    }

    public void setTopicReplies(Integer topicReplies) {
        this.topicReplies = topicReplies;
    }

    public Boolean getTopicDeleted() {
        return topicDeleted;
    }

    public void setTopicDeleted(Boolean topicDeleted) {
        this.topicDeleted = topicDeleted;
    }

    public Boolean getTopicExcellent() {
        return topicExcellent;
    }

    public void setTopicExcellent(Boolean topicExcellent) {
        this.topicExcellent = topicExcellent;
    }

    public String getTopicUserPhone() {
        return topicUserPhone;
    }

    public void setTopicUserPhone(String topicUserPhone) {
        this.topicUserPhone = topicUserPhone == null ? null : topicUserPhone.trim();
    }

    public String getTopicLastReplyUserPhone() {
        return topicLastReplyUserPhone;
    }

    public void setTopicLastReplyUserPhone(String topicLastReplyUserPhone) {
        this.topicLastReplyUserPhone = topicLastReplyUserPhone == null ? null : topicLastReplyUserPhone.trim();
    }
}