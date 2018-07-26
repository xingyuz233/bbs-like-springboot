package com.example.demo.model;

public class Relation extends RelationKey {
    private Boolean followState;

    private Boolean blockState;

    private Boolean friendState;

    public Boolean getFollowState() {
        return followState;
    }

    public void setFollowState(Boolean followState) {
        this.followState = followState;
    }

    public Boolean getBlockState() {
        return blockState;
    }

    public void setBlockState(Boolean blockState) {
        this.blockState = blockState;
    }

    public Boolean getFriendState() {
        return friendState;
    }

    public void setFriendState(Boolean friendState) {
        this.friendState = friendState;
    }
}