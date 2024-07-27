package com.example.epod.auth.service;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.os.Binder;

import androidx.annotation.Nullable;
import com.example.epod.auth.data.model.Auth;
import com.example.epod.auth.data.repository.AuthCallback;
import com.example.epod.auth.data.repository.AuthRepository;

public class AuthService extends Service {
    private final IBinder binder = new LocalBinder();

    private static final String PREFERENCES_NAME = "auth";
    private static final String KEY_TOKEN = "token";
    private static final String KEY_USER_ID = "userId";
    private static final String KEY_AUTHORIZATION = "authorization";
    private static final String KEY_ACCOUNT = "account";
    private static final String KEY_COMPANY = "company";
    private static final String KEY_LOCATION = "location";
    private static final String KEY_USER_GROUP = "user_group";

    private SharedPreferences sharedPreferences;
    private AuthRepository authRepository;

    @Override
    public void onCreate() {
        super.onCreate();
        sharedPreferences = getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        authRepository = new AuthRepository(this, new AuthCallback() {
            @Override
            public void onLogin(Auth authenticatedUser) {

            }

            @Override
            public void onError(String errorMessage) {

            }
        });
    }

    public void login(String username, String password, AuthCallback authCallback) {
        authRepository.login(username, password, new AuthCallback() {
            @Override
            public void onLogin(Auth authenticatedUser) {
                @SuppressLint("CommitPrefEdits") SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.putString(KEY_TOKEN, authenticatedUser.getAccess_token());
                editor.putString(KEY_USER_ID, authenticatedUser.getId());
                editor.putString(KEY_ACCOUNT, authenticatedUser.getCurrentAccount().getUUID());
                editor.putString(KEY_LOCATION, authenticatedUser.getCurrentLocation().getUUID());
                editor.putString(KEY_COMPANY, authenticatedUser.getCurrentCompany().getUUID());
                editor.putString(KEY_USER_GROUP, authenticatedUser.getCurrentUserGroup().getUUID());
            }

            @Override
            public void onError(String errorMessage) {
                authCallback.onError(errorMessage);
            }
        });
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

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public class LocalBinder extends Binder {
        public AuthService getService() {
            return AuthService.this;
        }
    }
}
