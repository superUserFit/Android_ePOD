package com.example.epod.auth.data.repository;

import android.content.Context;
import com.example.epod.auth.data.model.Auth;

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
                if (response.isSuccessful()) {
                    AuthResponse authResponse = response.body();
                    if(authResponse != null) {
                        Auth authenticatedUser = authResponse.getAuthenticatedUser();
                        authCallback.onLogin(authenticatedUser);
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

    @Override
    public void logout(AuthCallback authCallback) {

    }
}
