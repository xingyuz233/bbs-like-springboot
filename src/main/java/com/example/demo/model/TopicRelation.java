package com.example.demo.model;

public class TopicRelation extends TopicRelationKey {
    private Boolean favoriteState;

    private Boolean followState;

    private Boolean likeState;

    public Boolean getFavoriteState() {
        return favoriteState;
    }

    public void setFavoriteState(Boolean favoriteState) {
        this.favoriteState = favoriteState;
    }

    public Boolean getFollowState() {
        return followState;
    }

    public void setFollowState(Boolean followState) {
        this.followState = followState;
    }

    public Boolean getLikeState() {
        return likeState;
    }

    public void setLikeState(Boolean likeState) {
        this.likeState = likeState;
    }
}