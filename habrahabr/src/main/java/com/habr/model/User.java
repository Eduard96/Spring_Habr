package com.habr.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "user")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler",
                       "followers", "articles", "reactionCounter"})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 2, max = 25, message = "firstName Should be more than 2 and less than 25")
    @Column(name = "first_name")
    private String firstName;

    @Size(min = 2, max = 25, message = "lastName Should be more than 2 and less than 25")
    @Column(name = "last_name")
    private String lastName;

    @Size(min = 5, max = 30, message = "userName should be more than 5 and less than 30")
    @Column(unique = true, name = "user_name")
    private String userName;

    @Email
    @Column (unique = true)
    private String email;

    @Size(min = 8, max = 120, message = "Should be more than 8 and less than 20")
    @Column(nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "email_verified")
    private Boolean emailVerified;

//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
//    @Column(nullable = false, name = "creation_date",columnDefinition="DATETIME")
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date creationDate;

//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(name = "user_following",
//            joinColumns = {@JoinColumn(referencedColumnName = "id")},
//            inverseJoinColumns = {@JoinColumn(referencedColumnName = "id")})
//    @Fetch(FetchMode.SELECT)
//    private Set<User> following;

    @Transient
    private int followingNumber;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_followers",
            joinColumns = {@JoinColumn(referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(referencedColumnName = "id")})
    private Set<User> followers;

    @Transient
    private int followersNumber;


    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "user_id")
    private Set<Article> articles;

    @Transient
    private int articlesNumber;

    @OneToMany( fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "user_id")
    private Set<ReactionCounter> reactionCounter;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String name) {
        this.firstName = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String nickname) {
        this.userName = nickname;
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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String surname) {
        this.lastName = surname;
    }

    public Boolean getEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(Boolean emailVerified) {
        this.emailVerified = emailVerified;
    }

//    public Date getCreationDate() {
//        return creationDate;
//    }
//
//    public void setCreationDate(Date creationDate) {
//        this.creationDate = creationDate;
//    }

    public Set<User> getFollowers() {
        return followers;
    }

    public void setFollowers(Set<User> followers) {
        this.followers = followers;
        setFollowersNumber(followersNumber);
    }

    public Set<Article> getArticles() {
        return articles;
    }

    public void setArticles(Set<Article> articles) {
        this.articles = articles;
    }

    public Set<ReactionCounter> getReactionCounter() {
        return reactionCounter;
    }

    public void setReactionCounter(Set<ReactionCounter> reactionCounter) {
        this.reactionCounter = reactionCounter;
        articlesNumber = this.articles.size();
    }

    public int getFollowingNumber() {
        return followingNumber;
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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + firstName + '\'' +
                ", surname='" + lastName + '\'' +
                ", nickname='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", emailVerified=" + emailVerified +
                ", followingNumber=" + followingNumber +
                ", followers=" + followers +
                ", followersNumber=" + followersNumber +
                ", articles=" + articles +
                ", articlesNumber=" + articlesNumber +
                ", reactionCounter=" + reactionCounter +
                '}';
    }
}
