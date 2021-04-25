package com.habr.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "reaction_counter",
        uniqueConstraints = @UniqueConstraint(columnNames={"user_id", "article_id", "reaction_id"}))
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "reaction", "user", "article"})
public class ReactionCounter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", insertable = false, updatable = false)
    private Long user_id;
    @Column(name = "article_id", insertable = false, updatable = false)
    private Long article_id;
    @Column(name = "reaction_id", insertable = false, updatable = false)
    private int reaction_id;
//    @Column(name = "reaction_id", insertable = false, updatable = false)
//    private String reaction_name;

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
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Long getArticle_id() {
        return article_id;
    }

    public void setArticle_id(Long article_id) {
        this.article_id = article_id;
    }

    public int getReaction_id() {
        return reaction_id;
    }

    public void setReaction_id(int reaction_id) {
        this.reaction_id = reaction_id;
    }

//    public String getReaction_name() {
//        return getReaction().getReactionName();
//    }
//
//    public void setReaction_name(String reaction_name) {
//        this.reaction_name = reaction_name;
//    }
}
