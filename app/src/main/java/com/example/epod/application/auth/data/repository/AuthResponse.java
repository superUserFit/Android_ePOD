package com.example.epod.application.auth.data.repository;

import com.example.epod.application.auth.data.model.Auth;
import com.google.gson.annotations.SerializedName;

public class AuthResponse {
    @SerializedName("data")
    private Auth user;

    public Auth getAuthenticatedUser() {
        return user;
    }
}
