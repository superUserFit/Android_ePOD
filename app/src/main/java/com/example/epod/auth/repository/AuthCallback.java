package com.example.epod.auth.repository;

import com.example.epod.auth.model.Auth;


public interface AuthCallback {
    void onLoadAuth(Auth authenticatedUser);
    void onError(String errorMessage);
}
