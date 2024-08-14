package com.example.epod.screens.job_management.job_order.dependency_injection;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.epod.screens.job_management.job_order.main.model.JobOrderViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class JobOrderViewModelModule {
    @Binds
    @IntoMap
    @JobOrderViewModelKey(JobOrderViewModel.class)
    abstract ViewModel bindJobOrderViewModel(JobOrderViewModel viewModel);

    @Binds
    abstract ViewModelProvider.Factory bindFactory(JobOrderViewModelFactory factory);
}
