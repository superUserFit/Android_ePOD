package com.example.epod.application.auth.domain.repository;

public interface AuthRepositoryInterface {
    void login(String username, String password);
    void logout();
}
