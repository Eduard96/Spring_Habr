package com.habr.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "article")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "reactionCounter"})

public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column(name = "publication_date",columnDefinition="DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date publicationDate;

    @Column(name = "article_content",columnDefinition = "text")
    private String articleContent;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @Transient
    private Long user_id;

    @OneToMany(mappedBy = "article", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private Set<ReactionCounter> reactionCounter;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getArticleContent() {
        return articleContent;
    }

    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getUser_id() {
        setUser_id(getUser().getId());
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Set<ReactionCounter> getReactionCounter() {
        return reactionCounter;
    }

    public void setReactionCounter(Set<ReactionCounter> reactionCounter) {
        this.reactionCounter = reactionCounter;
    }
}
