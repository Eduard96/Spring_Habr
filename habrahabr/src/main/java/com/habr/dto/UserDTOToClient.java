package com.habr.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;

public class UserDTOToClient implements Serializable {

    @NotBlank
    @Size(min = 3, max = 20, message = "Name Should be more than 2 and less than 25")
    private String firstName;

    @NotBlank
    @Size(min = 3, max = 20, message = "Surname Should be more than 2 and less than 25")
    private String lastName;

    @NotBlank
    @Size(min = 3, max = 20, message = "Nickname should be more than 5 and less than 30")
    private String userName;

    @NotBlank(message = "email must not be empty")
    @Email
    private String email;

    @NotBlank
    @Size(min = 8, max = 20, message = "Should be more than 8 and less than 20")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    private Set<String> roles;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
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
}

