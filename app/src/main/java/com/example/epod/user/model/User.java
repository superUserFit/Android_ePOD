package com.example.epod.user.model;

public class User {
    private String id, username, access_token;

    public User(String id, String username, String access_token) {
        this.id = id;
        this.username = username;
        this.access_token = access_token;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }
}
