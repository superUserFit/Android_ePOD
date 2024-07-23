package com.example.epod.auth.service;

import android.content.Context;
import android.content.SharedPreferences;
import com.example.epod.auth.model.Auth;

public class AuthService {
    private static final String PREFERENCES_NAME = "auth";
    private static final String KEY_TOKEN = "token";
    private static final String KEY_USER_ID = "userId";

    private final SharedPreferences sharedPreferences;

    public AuthService(Context context) {
        this.sharedPreferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    public void login(Auth authenticatedUser) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_TOKEN, authenticatedUser.getAccess_token());
        editor.putString(KEY_USER_ID, authenticatedUser.getId());
        editor.apply();
    }

    public String getToken() {
        return sharedPreferences.getString(KEY_TOKEN, null);
    }

    public String getUserId() {
        return sharedPreferences.getString(KEY_USER_ID, null);
    }

    public void logout() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(KEY_TOKEN);
        editor.remove(KEY_USER_ID);
        editor.apply();
    }
}
