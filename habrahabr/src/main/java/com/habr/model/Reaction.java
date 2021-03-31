package com.habr.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "reaction")
public class Reaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "reaction_name")
    private String reactionName;

    @OneToMany(mappedBy = "reaction", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private Set<ReactionCounter> reactionCounter;

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

    public Set<ReactionCounter> getReactionCounter() {
        return reactionCounter;
    }

    public void setReactionCounter(Set<ReactionCounter> reactionCounter) {
        this.reactionCounter = reactionCounter;
    }
}
