package com.example.epod.application;

import android.content.Context;

import com.example.epod.application.job_order.domain.repository.api.JobOrderAPI;
import com.example.epod.screens.dependency_injection.ViewModelModule;
import com.example.epod.utils.Request;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module(includes = ViewModelModule.class)
public class NetworkModule {
    @Provides
    @Singleton
    Retrofit provideRetrofit(Context context) {
        return Request.getRetrofit(context);
    }

    @Provides
    @Singleton
    JobOrderAPI provideJobOrderAPI(Retrofit retrofit) {
        return retrofit.create(JobOrderAPI.class);
    }
}
