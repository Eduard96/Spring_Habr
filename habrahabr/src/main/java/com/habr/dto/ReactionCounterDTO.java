package com.habr.dto;

public class ReactionCounterDTO {

    private Long id;

    private Long user_id;
    private Long article_id;
    private int reaction_id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

}
