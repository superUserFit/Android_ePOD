package com.example.epod.dependency_injection.job_order.data;

import android.app.Application;
import android.content.Context;
import com.example.epod.application.job_order.domain.repository.JobOrderRepository;
import com.example.epod.application.job_order.domain.repository.api.JobOrderAPI;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class JobOrderModule {
    @Provides
    static JobOrderRepository provideRepository(JobOrderAPI jobOrderAPI, Context context) {
        return new JobOrderRepository(jobOrderAPI, context);
    }

    @Provides
    static JobOrderAPI provideApi(Retrofit retrofit) {
        return retrofit.create(JobOrderAPI.class);
    }

    @Provides
    static Context provideContext(Application application) {
        return application.getApplicationContext();
    }
}
