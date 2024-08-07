package com.example.epod.auth.service;

public interface AuthServiceInterface {
    String getAuthorization();
    String getUserId();
    String getToken();
    String getAccount();
    String getCompany();
    String getLocation();
    String getUserGroup();
    String getUserRole();
}
