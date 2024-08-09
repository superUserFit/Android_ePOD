package com.example.epod.application.auth.data.repository;

public interface AuthRepositoryInterface {
    void login(String username, String password, AuthCallback authCallback);
    void logout(AuthCallback authCallback);
}
