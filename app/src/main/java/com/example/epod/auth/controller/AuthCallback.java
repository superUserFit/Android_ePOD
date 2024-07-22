package com.example.epod.auth.controller;

import com.example.epod.auth.model.Auth;

public interface AuthCallback {
    void onLoadAuth(Auth authenticatedUser);
    void onError(String errorMessage);
}
