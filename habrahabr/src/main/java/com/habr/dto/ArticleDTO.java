package com.habr.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.habr.model.Article;

import java.util.Date;

public class ArticleDTO {

    private Long id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date publicationDate;
    private String title;
    private String articleContent;

    public ArticleDTO() {
    }

    public ArticleDTO(Article article) {
        this.id = article.getId();
        this.title = article.getTitle();
        this.articleContent = article.getArticleContent();
        this.publicationDate = article.getPublicationDate();
    }

    public Long getId() {
        return id;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public String getTitle() {
        return title;
    }

    public String getArticleContent() {
        return articleContent;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent;
    }
}
