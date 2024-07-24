package com.example.epod.auth.data.repository;

public interface AuthRepositoryInterface {
    void login(String username, String password, AuthCallback authCallback);
    void logout(AuthCallback authCallback);
}
