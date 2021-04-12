package com.habr.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "reaction_counter")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "reaction", "user", "article"})
public class ReactionCounter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Transient
    private Long user_id;

    @ManyToOne
    private User user;
    @ManyToOne
    private Article article;
    @ManyToOne
    private Reaction reaction;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public Reaction getReaction() {
        return reaction;
    }

    public void setReaction(Reaction reaction) {
        this.reaction = reaction;
    }

    public Long getUser_id() {
        return getUser().getId();
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }
}
