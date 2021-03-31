package com.habr.model;

import java.io.Serializable;
import java.util.Objects;

public class FollowersPK implements Serializable {

    private User following;

    private User followers;

    public FollowersPK(User following, User followers) {
        this.following = following;
        this.followers = followers;
    }

    public FollowersPK() {
    }

    public User getFollowing() {
        return following;
    }

    public void setFollowing(User following) {
        this.following = following;
    }

    public User getFollowers() {
        return followers;
    }

    public void setFollowers(User followers) {
        this.followers = followers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FollowersPK that = (FollowersPK) o;
        return getFollowing().equals(that.getFollowing()) && getFollowers().equals(that.getFollowers());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFollowing(), getFollowers());
    }
}
