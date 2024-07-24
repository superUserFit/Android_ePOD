package com.example.epod.auth.data.repository;

import com.example.epod.auth.data.model.Auth;


public interface AuthCallback {
    void onLogin(Auth authenticatedUser);
    void onError(String errorMessage);
}
