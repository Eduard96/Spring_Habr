package com.habr.model;

import javax.persistence.*;

@Entity
@Table
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String articleName;
    @Column
    private String articleAuthor;
    @Column
    private Long numberOfViews;
    @Column
    private String articlePath;
    @ManyToOne
    private User user;



}
