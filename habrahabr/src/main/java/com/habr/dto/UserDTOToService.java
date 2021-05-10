package com.habr.dto;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

public class UserDTOToService {

    @NotBlank
    @Size(min = 3, max = 20, message = "Firstname too short")
    private String firstName;

    @NotBlank
    @Size(min = 3, max = 20, message = "Lastname too short")
    private String lastName;

    @NotBlank
    @Size(min = 3, max = 20, message = "Username too short")
    private String userName;

    @Email
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank
    @Size(min = 8, max = 20)
    private String password;

    private Set<String> roles;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        String temp = password;
        password = null;
        return temp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

//    @Override
//    public String toString() {
//        return "{" +
//                " \"firstName\":\"" + firstName + '\"' +
//                ", \"lastName\":\"" + lastName + '\"' +
//                ", \"userName\":\"" + userName + '\"' +
//                ", \"password\":\"" + password + '\"' +
//                ", \"roles\":[" + "\"user\"" +
//                "]}";
//    }
}
