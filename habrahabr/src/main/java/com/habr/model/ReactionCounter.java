package com.habr.model;

import javax.persistence.*;

@Entity
@Table(name = "reaction_counter")
@IdClass(ReactionCounterPK.class)
public class ReactionCounter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Id
    @Column(name = "article_id", updatable = false, insertable = false)
    private Long articleId;
    @Id
    @Column(name = "user_id", updatable = false, insertable = false)
    private Long userId;
    @Id
    @Column(name = "reactionId", updatable = false, insertable = false)
    private Long reactionId;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    private Article article;
    @ManyToOne(fetch = FetchType.LAZY)
    private Reaction reaction;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getReactionId() {
        return reactionId;
    }

    public void setReactionId(Long reaction_id) {
        this.reactionId = reaction_id;
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
}
