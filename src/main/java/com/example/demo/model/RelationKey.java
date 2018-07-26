package com.example.demo.model;

public class RelationKey {
    private String bottomUser;

    private String topUser;

    public String getBottomUser() {
        return bottomUser;
    }

    public void setBottomUser(String bottomUser) {
        this.bottomUser = bottomUser == null ? null : bottomUser.trim();
    }

    public String getTopUser() {
        return topUser;
    }

    public void setTopUser(String topUser) {
        this.topUser = topUser == null ? null : topUser.trim();
    }
}