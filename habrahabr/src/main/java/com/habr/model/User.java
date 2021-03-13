package com.habr.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import javax.xml.crypto.Data;
import java.util.Set;

@Entity
@Table
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
    private String lastname;
    @NotEmpty
    @Size(min = 5, max = 30, message = "Should be more than 5 and less than 30")
    @Column(unique = true)
    private String nickname;
    @Email
    @Column (unique = true)
    private String email;
    @NotEmpty
    @Size(min = 8, max = 20, message = "Should be more than 8 and less than 20")
    @Column(unique = true)
    private String login;
    @NotEmpty
    @Size(min = 8, max = 20, message = "Should be more than 8 and less than 20")
    @Column
    private String password;
    @Column
    private Integer followers;
    @Column
    private Integer following;

    @OneToMany
    private Set<Article> articles;

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

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getFollowers() {
        return followers;
    }

    public void setFollowers(Integer followers) {
        this.followers = followers;
    }

    public Integer getFollowing() {
        return following;
    }

    public void setFollowing(Integer following) {
        this.following = following;
    }

    public Set<Article> getArticles() {
        return articles;
    }

    public void setArticles(Set<Article> articles) {
        this.articles = articles;
    }
}
