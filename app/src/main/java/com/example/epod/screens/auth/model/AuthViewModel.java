package com.example.epod.screens.auth.model;


import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.epod.application.auth.data.model.Auth;
import com.example.epod.application.auth.domain.repository.AuthRepository;
import com.example.epod.utils.helpers.ViewHelper;


public class AuthViewModel extends AndroidViewModel {
    private final AuthRepository authRepository;
    private final LiveData<Auth> authenticatedUser;
    private final LiveData<ViewHelper.LoadingState> loadingState;

    public AuthViewModel(@NonNull Application application) {
        super(application);
        this.authRepository = new AuthRepository(application.getApplicationContext());
        this.authenticatedUser = authRepository.getAuthenticatedUser();
        this.loadingState = authRepository.getLoadingState();
    }

    public void login(String username, String password) {
        authRepository.login(username, password);
    }

    public LiveData<Auth> getAuthenticatedUser() {
        return authenticatedUser;
    }

    public LiveData<ViewHelper.LoadingState> getLoadingState() { return loadingState; }
}
