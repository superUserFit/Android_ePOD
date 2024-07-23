package com.example.epod.auth.repository;


import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

import com.example.epod.auth.controller.AuthResponse;


public interface AuthRepository {
    @FormUrlEncoded
    @POST("site/api/site/login")
    Call<AuthResponse> login(
            @Field("username") String username,
            @Field("password") String password
    );
}