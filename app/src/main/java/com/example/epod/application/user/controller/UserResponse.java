package com.example.epod.application.user.controller;

import com.example.epod.application.user.model.User;
import com.google.gson.annotations.SerializedName;

public class UserResponse {
    @SerializedName("data")
    private User user;

    public User getUser() {
        return user;
    }
}
