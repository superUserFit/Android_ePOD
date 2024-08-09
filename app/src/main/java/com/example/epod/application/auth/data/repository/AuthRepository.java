package com.example.epod.application.auth.data.repository;

import android.content.Context;
import android.util.Log;
import com.example.epod.application.auth.data.model.Auth;

import com.example.epod.utils.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthRepository implements AuthRepositoryInterface {
    private final AuthAPI authApi;

    public AuthRepository(Context context, AuthCallback authCallback) {
        this.authApi = Request.getRetrofitInstance(context).create(AuthAPI.class);
    }

    @Override
    public void login(String username, String password, AuthCallback authCallback) {
        Call<AuthResponse> call = authApi.login(username, password);

        call.enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                try {
                    AuthResponse authResponse = response.body();
                    Auth authenticatedUser = authResponse.getAuthenticatedUser();
                    authCallback.onLogin(authenticatedUser);
                } catch (Exception error) {
                    authCallback.onError("Invalid username or password.");
                }
            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable throwable) {
                Log.e("Repo", throwable.getMessage());
                authCallback.onError("Invalid username or password");
            }
        });
    }

    @Override
    public void logout(AuthCallback authCallback) {

    }
}
