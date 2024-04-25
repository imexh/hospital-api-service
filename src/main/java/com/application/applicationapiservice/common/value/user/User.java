package com.application.applicationapiservice.common.value.user;

import com.application.applicationapiservice.common.enums.UserType;

public class User {
    private String id;
    private String username;
    private String name;
    private String email;
    private UserType userType;
    private String password;

    public User(String id, String username, String name, String email, UserType userType, String password) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.email = email;
        this.userType = userType;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public UserType getUserType() {
        return userType;
    }

    public String getPassword() {
        return password;
    }
}
