package com.habr.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import javax.xml.crypto.Data;
import java.util.Set;

@Entity
@Table
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Size(min = 2, max = 25, message = "Should be more than 2 and less than 25")
    @Column
    private String name;
    @NotEmpty
    @Size(min = 2, max = 25, message = "Should be more than 2 and less than 25")
    @Column
    private String lastname;
    @NotEmpty
    @Size(min = 5, max = 30, message = "Should be more than 5 and less than 30")
    @Column(unique = true)
    private String nickName;
    @Email
    @Column (unique = true)
    private String email;
    @NotEmpty
    @Size(min = 8, max = 20, message = "Should be more than 8 and less than 20")
    @Column(unique = true)
    private String login;
    @NotEmpty
    @Size(min = 8, max = 20, message = "Should be more than 8 and less than 20")
    @Column
    private String password;
    @Column
    private Integer followers;
    @Column
    private Integer following;

    @OneToMany
    private Set<Article> articles;
}
