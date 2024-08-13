package com.example.epod.application.job_order.data.module;


import com.example.epod.application.job_order.domain.repository.api.JobOrderAPI;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class JobOrderModule {
    @Provides
    static JobOrderAPI providerJobOrderAPI(Retrofit retrofit) {
        return retrofit.create(JobOrderAPI.class);
    }
}
