package com.example.epod.auth.repository;


import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


public interface AuthRepositoryInterface {
    @FormUrlEncoded
    @POST("site/api/site/login")
    Call<AuthResponse> login(
            @Field("username") String username,
            @Field("password") String password
    );
}