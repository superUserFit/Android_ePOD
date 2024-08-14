package com.example.epod.application;

import android.content.SharedPreferences;

import com.example.epod.MainActivity;
import com.example.epod.application.job_order.domain.repository.JobOrderRepository;
import com.example.epod.application.job_order.domain.repository.api.JobOrderAPI;
import com.example.epod.screens.job_management.job_order.dependency_injection.JobOrderViewModelModule;
import com.example.epod.screens.job_management.job_order.main.JobOrderFragment;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;


@Singleton
@Component(modules = {NetworkModule.class, AppModule.class, JobOrderViewModelModule.class})
public interface AppComponent {
    Retrofit provideRetrofit();
    JobOrderAPI provideJobOrderAPI();
    JobOrderRepository provideJobOrderRepository();
    SharedPreferences provideSharedPreferences();

    void inject(MainActivity mainActivity);
    void inject(JobOrderFragment jobOrderFragment);
}
