package com.example.epod.auth.view;


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

import com.example.epod.auth.data.model.Auth;
import com.example.epod.auth.data.repository.AuthCallback;
import com.example.epod.auth.service.AuthService;

public class AuthViewModel extends AndroidViewModel {
    private final MutableLiveData<Auth> authenticatedUser = new MutableLiveData<>();
    private final MutableLiveData<String> errorMessage = new MutableLiveData<>();
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
            authService.login(username, password, new AuthCallback() {
                @Override
                public void onLogin(Auth user) {
                    authenticatedUser.postValue(user);
                }

                @Override
                public void onError(String message) {
                    errorMessage.postValue(message);
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

    @Override
    protected  void onCleared() {
        super.onCleared();
        getApplication().unbindService(serviceConnection);
    }
}
