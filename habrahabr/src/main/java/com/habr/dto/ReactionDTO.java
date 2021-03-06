package com.habr.dto;

import java.io.Serializable;

public class ReactionDTO implements Serializable {

    private int id;
    private String reactionName;
    private Long user_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReactionName() {
        return reactionName;
    }

    public void setReactionName(String reactionName) {
        this.reactionName = reactionName;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }
}
