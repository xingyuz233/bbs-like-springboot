package com.example.demo.model;

import java.util.Date;

public class Reply {
    private Integer replyId;

    private String replyContent;

    private Date replyModifyTime;

    private Date replyPublishTime;

    private String replyUserPhone;

    private Integer replyTopicId;

    public Integer getReplyId() {
        return replyId;
    }

    public void setReplyId(Integer replyId) {
        this.replyId = replyId;
    }

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent == null ? null : replyContent.trim();
    }

    public Date getReplyModifyTime() {
        return replyModifyTime;
    }

    public void setReplyModifyTime(Date replyModifyTime) {
        this.replyModifyTime = replyModifyTime;
    }

    public Date getReplyPublishTime() {
        return replyPublishTime;
    }

    public void setReplyPublishTime(Date replyPublishTime) {
        this.replyPublishTime = replyPublishTime;
    }

    public String getReplyUserPhone() {
        return replyUserPhone;
    }

    public void setReplyUserPhone(String replyUserPhone) {
        this.replyUserPhone = replyUserPhone == null ? null : replyUserPhone.trim();
    }

    public Integer getReplyTopicId() {
        return replyTopicId;
    }

    public void setReplyTopicId(Integer replyTopicId) {
        this.replyTopicId = replyTopicId;
    }
}