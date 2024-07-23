package com.example.epod.auth.repository;

import com.example.epod.auth.model.Auth;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthRepository {
    private final AuthRepositoryInterface authRepositoryInterface;
    private final AuthCallback authCallback;

    public AuthRepository(AuthRepositoryInterface authRepositoryInterface, AuthCallback authCallback) {
        this.authRepositoryInterface = authRepositoryInterface;
        this.authCallback = authCallback;
    }

    public void login(String username, String password) {
        Call<AuthResponse> call = authRepositoryInterface.login(username, password);

        call.enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                if (response.isSuccessful()) {
                    AuthResponse authResponse = response.body();
                    if(authResponse != null) {
                        Auth authenticatedUser = authResponse.getAuthenticatedUser();
                        authCallback.onLoadAuth(authenticatedUser);
                    } else {
                        authCallback.onError("Invalid username or password");
                    }
                }
            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable throwable) {
                authCallback.onError("Invalid username or password");
            }
        });
    }
}
