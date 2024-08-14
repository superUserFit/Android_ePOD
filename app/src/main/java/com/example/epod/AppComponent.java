package com.example.epod;

import android.content.SharedPreferences;

import com.example.epod.application.AppModule;
import com.example.epod.application.NetworkModule;
import com.example.epod.application.job_order.domain.repository.JobOrderRepository;
import com.example.epod.application.job_order.domain.repository.api.JobOrderAPI;
import com.example.epod.dependency_injection.job_order.component.JobOrderComponent;
import com.example.epod.dependency_injection.job_order.presentation.module.JobOrderViewModelModule;
import com.example.epod.screens.job_management.job_order.main.JobOrderFragment;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;


@Singleton
@Component(modules = {NetworkModule.class, AppModule.class, JobOrderComponent.class})
public interface AppComponent {
    Retrofit provideRetrofit();
    JobOrderAPI provideJobOrderAPI();
    JobOrderRepository provideJobOrderRepository();
    SharedPreferences provideSharedPreferences();

    void inject(MyApplication myApplication);
    void inject(MainActivity mainActivity);
    void inject(JobOrderFragment jobOrderFragment);
}
