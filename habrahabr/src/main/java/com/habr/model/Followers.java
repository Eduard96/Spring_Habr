package com.habr.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "followers")
//@IdClass(FollowersPK.class)
public class Followers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @Column(name = "followers_id", insertable = false, updatable = false)
    private Long followersId;
    @Column(name = "following_id", insertable = false, updatable = false)
    private Long followingId;

    public Long getFollowersId() {
        return followersId;
    }

    public void setFollowersId(Long followersId) {
        this.followersId = followersId;
    }

    public Long getFollowingId() {
        return followingId;
    }

    public void setFollowingId(Long followingId) {
        this.followingId = followingId;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    private User following;

    @ManyToOne(fetch = FetchType.EAGER)
    private User followers;

    public Followers() {
    }

    public Followers(User following, User followers) {
        this.following = following;
        this.followers = followers;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @JsonIgnore
    public User getFollowing() {
        return following;
    }

    public void setFollowing(User following) {
        this.following = following;
    }

    @JsonIgnore
    public User getFollowers() {
        return followers;
    }

    public void setFollowers(User followers) {
        this.followers = followers;
    }
}
