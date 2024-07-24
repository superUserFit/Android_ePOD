package com.example.epod.auth.data.repository;

import com.example.epod.auth.data.model.Auth;
import com.google.gson.annotations.SerializedName;

public class AuthResponse {
    @SerializedName("data")
    private Auth user;

    public Auth getAuthenticatedUser() {
        return user;
    }
}
