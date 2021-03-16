package com.habr.model;

import java.io.Serializable;

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
}
