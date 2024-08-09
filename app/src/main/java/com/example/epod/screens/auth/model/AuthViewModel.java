package com.example.epod.screens.auth.model;


import android.annotation.SuppressLint;
import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.epod.application.auth.data.model.Auth;
import com.example.epod.application.auth.data.repository.AuthCallback;
import com.example.epod.application.auth.service.AuthService;

public class AuthViewModel extends AndroidViewModel {
    private final MutableLiveData<Auth> authenticatedUser = new MutableLiveData<>();
    private final MutableLiveData<String> errorMessage = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isLoading = new MutableLiveData<>();


    @SuppressLint("StaticFieldLeak")
    private AuthService authService;

    public AuthViewModel(@NonNull Application application) {
        super(application);
        bindAuthService(application);
    }

    private void bindAuthService(Context context) {
        Intent intent = new Intent(context, AuthService.class);
        context.bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    private final ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            AuthService.LocalBinder binder = (AuthService.LocalBinder) service;
            authService = binder.getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            authService = null;
        }
    };

    public void login(String username, String password) {
        if(authService != null) {
            isLoading.setValue(true);
            authService.login(username, password, new AuthCallback() {
                @Override
                public void onLogin(Auth user) {
                    authenticatedUser.postValue(user);
                    isLoading.setValue(false);
                }

                @Override
                public void onError(String message) {
                    errorMessage.postValue(message);
                    isLoading.setValue(false);
                }
            });
        }
    }

    public LiveData<Auth> getAuthenticatedUser() {
        return authenticatedUser;
    }

    public LiveData<String> getErrorMessage() {
        return errorMessage;
    }

    public LiveData<Boolean> getIsLoading() { return isLoading; }

    @Override
    protected  void onCleared() {
        super.onCleared();
        getApplication().unbindService(serviceConnection);
    }
}
