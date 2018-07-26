package com.example.demo.model;

import java.util.Date;

public class User {
    private String userPhone;

    private String userName;

    private String userPassword;

    private Date userLastPasswordResetDate;

    private Date userCreatedDate;

    private Integer userTopicsCount;

    private Integer userRepliesCount;

    private Integer userFollowingCount;

    private Integer userFollowersCount;

    private Integer userFavoritesCount;

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone == null ? null : userPhone.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword == null ? null : userPassword.trim();
    }

    public Date getUserLastPasswordResetDate() {
        return userLastPasswordResetDate;
    }

    public void setUserLastPasswordResetDate(Date userLastPasswordResetDate) {
        this.userLastPasswordResetDate = userLastPasswordResetDate;
    }

    public Date getUserCreatedDate() {
        return userCreatedDate;
    }

    public void setUserCreatedDate(Date userCreatedDate) {
        this.userCreatedDate = userCreatedDate;
    }

    public Integer getUserTopicsCount() {
        return userTopicsCount;
    }

    public void setUserTopicsCount(Integer userTopicsCount) {
        this.userTopicsCount = userTopicsCount;
    }

    public Integer getUserRepliesCount() {
        return userRepliesCount;
    }

    public void setUserRepliesCount(Integer userRepliesCount) {
        this.userRepliesCount = userRepliesCount;
    }

    public Integer getUserFollowingCount() {
        return userFollowingCount;
    }

    public void setUserFollowingCount(Integer userFollowingCount) {
        this.userFollowingCount = userFollowingCount;
    }

    public Integer getUserFollowersCount() {
        return userFollowersCount;
    }

    public void setUserFollowersCount(Integer userFollowersCount) {
        this.userFollowersCount = userFollowersCount;
    }

    public Integer getUserFavoritesCount() {
        return userFavoritesCount;
    }

    public void setUserFavoritesCount(Integer userFavoritesCount) {
        this.userFavoritesCount = userFavoritesCount;
    }
}