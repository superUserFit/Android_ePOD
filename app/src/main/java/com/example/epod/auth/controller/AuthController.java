package com.example.epod.auth.controller;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.epod.auth.repository.AuthRepository;
import com.example.epod.auth.model.Auth;
import com.example.epod.auth.repository.AuthResponse;
import com.example.epod.utils.Request;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthController {
    private final AuthRepository authRepository = Request.getRetrofitInstance().create(AuthRepository.class);
    private final AuthCallback authCallback;
    private final SharedPreferences sharedPreferences;

    public AuthController(Context context, AuthCallback authCallback) {
        this.sharedPreferences = context.getSharedPreferences("auth", Context.MODE_PRIVATE);
        this.authCallback = authCallback;
    }

    public void login(String username, String password) {
        Call<AuthResponse> call = authRepository.login(username, password);

        call.enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                if (response.isSuccessful()) {
                    AuthResponse authResponse = response.body();
                    if(authResponse != null) {
                        Auth authenticatedUser = authResponse.getAuthenticatedUser();

                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("token", authenticatedUser.getAccess_token());
                        editor.putString("userId", authenticatedUser.getId());
                        editor.apply();

                        authCallback.onLoadAuth(authResponse.getAuthenticatedUser());
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
