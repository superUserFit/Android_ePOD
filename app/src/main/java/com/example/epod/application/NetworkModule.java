package com.example.epod.application;

import android.content.Context;

import com.example.epod.application.job_order.domain.repository.api.JobOrderAPI;
import com.example.epod.dependency_injection.ApplicationContext;
import com.example.epod.dependency_injection.job_order.presentation.module.JobOrderViewModelModule;
import com.example.epod.utils.Request;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module(includes = JobOrderViewModelModule.class)
public class NetworkModule {
    @Provides
    @Singleton
    Retrofit provideRetrofit(@ApplicationContext Context context) {
        return Request.getRetrofit(context);
    }
}
