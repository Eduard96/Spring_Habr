package com.habr.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Size(min = 2, max = 25, message = "Should be more than 2 and less than 25")
    @Column
    private String name;

    @NotEmpty
    @Size(min = 2, max = 25, message = "Should be more than 2 and less than 25")
    @Column
    private String surname;

    @NotEmpty
    @Size(min = 5, max = 30, message = "Should be more than 5 and less than 30")
    @Column(unique = true)
    private String nickname;

    @NotEmpty
    @Email
    @Column (unique = true)
    private String email;


    @NotEmpty 
    @Size(min = 8, max = 20, message = "Should be more than 8 and less than 20")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column
    private String password;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "email_verified")
    private Boolean emailVerified;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "creation_date",columnDefinition="DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

    @OneToMany(mappedBy = "following", fetch = FetchType.EAGER)
    private Set<Followers> following;

    @Transient
    private int followingNumber;

    
    @OneToMany(mappedBy = "followers", fetch = FetchType.EAGER)
    private Set<Followers> followers;

    @Transient
    private int followersNumber;

    
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private Set<Article> articles;

    @Transient
    private int articlesNumber;

    
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
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

    @JsonIgnore
    public Set<Followers> getFollowing() {
        return following;
    }

    public void setFollowing(Set<Followers> following) {
        this.following = following;
        setFollowingNumber(followingNumber);
    }

    @JsonIgnore
    public Set<Followers> getFollowers() {
        return followers;
    }

    public void setFollowers(Set<Followers> followers) {
        this.followers = followers;
        setFollowersNumber(followersNumber);
    }

    @JsonIgnore
    public Set<Article> getArticles() {
        return articles;
    }

    public void setArticles(Set<Article> articles) {
        this.articles = articles;
    }

    @JsonIgnore
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

    public void setFollowingNumber(int followingNumber) {
        this.followingNumber = followingNumber;
    }

    public void setFollowersNumber(int followersNumber) {
        this.followersNumber = followersNumber;
    }

    public void setArticlesNumber(int articlesNumber) {
        this.articlesNumber = articlesNumber;
    }
}
