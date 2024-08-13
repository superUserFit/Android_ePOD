package com.example.epod.application;

import com.example.epod.MainActivity;
import com.example.epod.application.job_order.domain.repository.JobOrderRepository;
import com.example.epod.screens.job_management.job_order.main.JobOrderFragment;
import com.example.epod.utils.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

@Singleton
@Component(modules = {NetworkModule.class, AppModule.class})
public interface AppComponent {
    Retrofit provideRetrofit();
    JobOrderRepository provideJobOrderRepository();

    void inject(MainActivity mainActivity);
    void inject(JobOrderFragment jobOrderFragment);
}
