package com.example.demo.controller.response;

import com.example.demo.model.TopicReply;

import java.io.Serializable;
import java.text.SimpleDateFormat;

public class TopicReplyDetail implements Serializable {
    private int id;                 // 回复 的 id
    private String body_html;       // 回复内容详情(HTML)
    private String created_at;      // 创建时间
    private String updated_at;      // 更新时间
    private boolean deleted;        // 是否已经删除
    private int topic_id;           // topic 的 id
    private UserBrief user;              // 创建该回复的用户信息
    private int likes_count;        // 喜欢的人数
    private Abilities abilities;    // 当前用户所拥有的权限

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBody_html() {
        return body_html;
    }

    public void setBody_html(String body_html) {
        this.body_html = body_html;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public int getTopic_id() {
        return topic_id;
    }

    public void setTopic_id(int topic_id) {
        this.topic_id = topic_id;
    }

    public UserBrief getUser() {
        return user;
    }

    public void setUser(UserBrief user) {
        this.user = user;
    }

    public int getLikes_count() {
        return likes_count;
    }

    public void setLikes_count(int likes_count) {
        this.likes_count = likes_count;
    }

    public Abilities getAbilities() {
        return abilities;
    }

    public void setAbilities(Abilities abilities) {
        this.abilities = abilities;
    }

    public TopicReplyDetail(TopicReply topicReply) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");

        this.id = topicReply.getReplyId();
        this.body_html = topicReply.getReplyContent();
        this.created_at = df.format(topicReply.getReplyPublishTime());
        this.updated_at = df.format(topicReply.getReplyModifyTime());
        this.deleted = topicReply.getReplyDeleted();
        this.topic_id = topicReply.getReplyTopicId();
        this.user = null;
        this.likes_count = topicReply.getReplyLikesCount();
        this.abilities = new Abilities(true, true);
    }

}
