package com.example.demo.controller.response;

import com.example.demo.model.User;

import javax.rmi.CORBA.Util;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UserBrief implements Serializable {

    private int id;             // 唯一 id
    private String login;       // 登录用户名
    private String name;        // 昵称
    private String avatar_url;  // 头像链接

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getLogin() {
        return this.login;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public String getAvatar_url() {
        return this.avatar_url;
    }

    public UserBrief(User user) {
        this.id = 0;
        this.login = user.getUserPhone();
        this.name = user.getUserName();
    }

    public static List<UserBrief> getUserBriefList(List<User> userList) {
        List<UserBrief> userBriefList = new ArrayList<>();
        for (User user: userList)  {
            userBriefList.add(new UserBrief(user));
        }
        return userBriefList;
    }
}
