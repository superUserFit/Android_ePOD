package com.example.epod.screens.job_management.job_order.dependency_injection;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.epod.application.job_order.domain.repository.JobOrderRepository;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Provider;

public class JobOrderViewModelFactory implements ViewModelProvider.Factory {
    private final Map<Class<? extends ViewModel>, Provider<ViewModel>> viewModels;
    private final JobOrderRepository jobOrderRepository;

    @Inject
    public JobOrderViewModelFactory(Map<Class<? extends ViewModel>, Provider<ViewModel>> viewModels, JobOrderRepository jobOrderRepository) {
        this.viewModels = viewModels;
        this.jobOrderRepository = jobOrderRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        Provider<? extends ViewModel> view_model = viewModels.get(modelClass);

        if(viewModels == null) {
            for (Map.Entry<Class<? extends ViewModel>, Provider<ViewModel>> entryMap:viewModels.entrySet()) {
                if(modelClass.isAssignableFrom(entryMap.getKey())) {
                    view_model = entryMap.getValue();
                    break;
                }
            }
        }

        if(view_model == null) {
            throw new IllegalArgumentException("Unknown ViewModel class");
        }

        try {
            return (T) view_model.get();
        } catch (Exception error) {
            throw new RuntimeException(error.getMessage());
        }
    }
}
