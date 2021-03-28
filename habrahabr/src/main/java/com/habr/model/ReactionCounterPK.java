package com.habr.model;

import java.io.Serializable;
import java.util.Objects;

public class ReactionCounterPK implements Serializable {

    private Long articleId;
    private Long userId;
    private Long reactionId;

    public ReactionCounterPK() {
    }

    public ReactionCounterPK(Long articleId, Long userId, Long reactionId) {
        this.articleId = articleId;
        this.userId = userId;
        this.reactionId = reactionId;
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

    public void setReactionId(Long reactionId) {
        this.reactionId = reactionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReactionCounterPK that = (ReactionCounterPK) o;
        return articleId.equals(that.articleId) && userId.equals(that.userId) && reactionId.equals(that.reactionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(articleId, userId, reactionId);
    }
}
