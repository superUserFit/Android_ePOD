package com.example.epod.user.controller;

import com.example.epod.user.model.User;
import com.google.gson.annotations.SerializedName;

public class UserResponse {
    @SerializedName("data")
    private User user;

    public User getUser() {
        return user;
    }
}
