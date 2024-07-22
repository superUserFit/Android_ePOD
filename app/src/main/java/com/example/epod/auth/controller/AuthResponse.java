package com.example.epod.auth.controller;

import com.example.epod.auth.model.Auth;
import com.google.gson.annotations.SerializedName;

public class AuthResponse {
    @SerializedName("data")
    private Auth user;

    public Auth getAuthenticatedUser() {
        return user;
    }
}
