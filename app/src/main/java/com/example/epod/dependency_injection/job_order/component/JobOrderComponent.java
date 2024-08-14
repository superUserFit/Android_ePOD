package com.example.epod.dependency_injection.job_order.component;


import com.example.epod.dependency_injection.job_order.data.JobOrderModule;
import com.example.epod.dependency_injection.job_order.presentation.module.JobOrderViewModelModule;
import com.example.epod.screens.job_management.job_order.main.JobOrderFragment;
import dagger.Component;

@Component(modules = {JobOrderViewModelModule.class, JobOrderModule.class})
public interface JobOrderComponent {
    void inject(JobOrderFragment jobOrderFragment);
}
