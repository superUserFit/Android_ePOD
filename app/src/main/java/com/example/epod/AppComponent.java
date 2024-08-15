package com.example.epod;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.epod.application.AppModule;
import com.example.epod.application.NetworkModule;
import com.example.epod.application.job_order.domain.repository.JobOrderRepository;
import com.example.epod.application.job_order.domain.repository.api.JobOrderAPI;
import com.example.epod.dependency_injection.ApplicationContext;
import com.example.epod.dependency_injection.job_order.component.JobOrderComponent;
import com.example.epod.dependency_injection.job_order.data.JobOrderModule;
import com.example.epod.dependency_injection.job_order.presentation.module.JobOrderViewModelModule;
import com.example.epod.screens.job_management.job_order.main.JobOrderFragment;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;
import retrofit2.Retrofit;


@Singleton
@Component(modules = {
        AndroidInjectionModule.class,
        NetworkModule.class,
        AppModule.class,
        JobOrderModule.class
})
public interface AppComponent extends AndroidInjector<MyApplication> {
    @Component.Factory
    interface Factory extends AndroidInjector.Factory<MyApplication> {
        @Override
        AppComponent create(@BindsInstance MyApplication application);
    }
//
//    @ApplicationContext
//    Context provideApplicationContext();
//    Retrofit provideRetrofit();
//    JobOrderAPI provideJobOrderAPI();
//    JobOrderRepository provideJobOrderRepository();
//    SharedPreferences provideSharedPreferences();
//
//    void inject(MyApplication myApplication);
//    void inject(MainActivity mainActivity);
//    void inject(JobOrderFragment jobOrderFragment);
}
