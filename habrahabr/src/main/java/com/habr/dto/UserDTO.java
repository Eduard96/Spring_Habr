package com.habr.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.habr.model.Article;
import com.habr.model.ReactionCounter;
import com.habr.model.User;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

public class UserDTO implements Serializable {

    private Long id;

    private String name;

    private String surname;

    private String nickname;

    private String email;


    private String password;

    private Boolean emailVerified;

    private Date creationDate;

    private Set<UserDTO> following;

    private int followingNumber;

    
    private Set<User> followers;

    private int followersNumber;

    
    private Set<Article> articles;

    @Transient
    private int articlesNumber;

    
    private Set<ReactionCounter> reactionCounter;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Article> getArticles() {
        return articles;
    }

    public void setArticles(Set<Article> articles) {
        this.articles = articles;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Boolean getEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(Boolean emailVerified) {
        this.emailVerified = emailVerified;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Set<UserDTO> getFollowing() {
        return following;
    }

    public void setFollowing(Set<UserDTO> following) {
        this.following = following;
        followingNumber = this.following.size();
    }

    public Set<User> getFollowers() {
        return followers;
    }

    public void setFollowers(Set<User> followers) {
        this.followers = followers;
        followersNumber = this.followers.size();
    }

    public Set<ReactionCounter> getReactionCounter() {
        return reactionCounter;
    }

    public void setReactionCounter(Set<ReactionCounter> reactionCounter) {
        this.reactionCounter = reactionCounter;
        articlesNumber = this.articles.size();
    }

    public int getFollowingNumber() {
        return following.size();
    }

    public int getFollowersNumber() {
        return followers.size();
    }

    public int getArticlesNumber() {
        return articles.size();
    }
}

