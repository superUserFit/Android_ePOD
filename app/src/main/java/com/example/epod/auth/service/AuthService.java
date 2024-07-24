package com.example.epod.auth.service;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.epod.auth.model.Auth;
import com.example.epod.auth.repository.AuthCallback;
import com.example.epod.auth.repository.AuthRepository;
import com.example.epod.auth.repository.AuthRepositoryInterface;
import com.example.epod.utils.Request;

public class AuthService {
    private static final String PREFERENCES_NAME = "auth";
    private static final String KEY_TOKEN = "token";
    private static final String KEY_USER_ID = "userId";
    private static final String KEY_AUTHORIZATION = "authorization";
    private static final String KEY_ACCOUNT = "account";
    private static final String KEY_COMPANY = "company";
    private static final String KEY_LOCATION = "location";
    private static final String KEY_USER_GROUP = "user_group";

    private final SharedPreferences sharedPreferences;
    private final AuthRepository authRepository;

    public AuthService(Context context) {
        this.sharedPreferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        AuthRepositoryInterface authRepositoryInterface = Request.getRetrofitInstance(context).create(AuthRepositoryInterface.class);

        this.authRepository = new AuthRepository(authRepositoryInterface, new AuthCallback() {
            @Override
            public void onLoadAuth(Auth authenticatedUser) {
                login(authenticatedUser);
                ((AuthCallback) context).onLoadAuth(authenticatedUser);
            }

            @Override
            public void onError(String errorMessage) {
                Log.e("Auth Service error: ", errorMessage);
            }
        });
    }

    public void performLogin(String username, String password) {
        authRepository.login(username, password);
    }

    public void login(Auth authenticatedUser) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_TOKEN, authenticatedUser.getAccess_token());
        editor.putString(KEY_USER_ID, authenticatedUser.getId());
        editor.putString(KEY_AUTHORIZATION, "Basic " + authenticatedUser.getAuthorization());
        editor.putString(KEY_ACCOUNT, authenticatedUser.getCurrentAccount().getUUID());
        editor.putString(KEY_COMPANY, authenticatedUser.getCurrentCompany().getUUID());
        editor.putString(KEY_LOCATION, authenticatedUser.getCurrentLocation().getUUID());
        editor.putString(KEY_USER_GROUP, authenticatedUser.getCurrentUserGroup().getUUID());
        editor.apply();
    }

    public String getToken() {
        return sharedPreferences.getString(KEY_TOKEN, null);
    }

    public String getUserId() {
        return sharedPreferences.getString(KEY_USER_ID, null);
    }

    public String getAuthorization() { return sharedPreferences.getString(KEY_AUTHORIZATION, null); };


    public void logout() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(KEY_TOKEN);
        editor.remove(KEY_USER_ID);
        editor.remove(KEY_AUTHORIZATION);
        editor.remove(KEY_ACCOUNT);
        editor.remove(KEY_COMPANY);
        editor.remove(KEY_LOCATION);
        editor.remove(KEY_USER_GROUP);
        editor.apply();
    }
}
