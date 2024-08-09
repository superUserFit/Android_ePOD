package com.example.epod.application.auth.data.repository;

import com.example.epod.application.auth.data.model.Auth;


public interface AuthCallback {
    void onLogin(Auth authenticatedUser);
    void onError(String errorMessage);
}
