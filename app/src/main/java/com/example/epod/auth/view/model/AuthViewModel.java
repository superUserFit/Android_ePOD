package com.example.epod.auth.view.model;


import android.annotation.SuppressLint;
import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

import android.util.Log;
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
            Log.e("Connection", "Service connected");
            AuthService.LocalBinder binder = (AuthService.LocalBinder) service;
            authService = binder.getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.e("Connection", "Service disconnected");
            authService = null;
        }
    };

    public void login(String username, String password) {
        Log.e("View model: ", username);
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
        } else {
            Log.e("Connection", "Auth Service not connected");
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
