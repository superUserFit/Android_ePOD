package com.example.epod.screens.job_management.job_order.details.model;

import android.annotation.SuppressLint;
import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.epod.application.job_order.data.model.JobOrderModel;
import com.example.epod.application.job_order.domain.service.JobOrderBoundService;
import org.jetbrains.annotations.NotNull;

public class JobOrderDetailsViewModel extends AndroidViewModel {
    @SuppressLint("StaticFieldLeak")
    private final JobOrderBoundService jobOrderBoundService;

    private final MutableLiveData<JobOrderModel> jobOrder;
    private final MutableLiveData<Boolean> isLoading;
    private final MutableLiveData<String> errorMessage;

    public JobOrderDetailsViewModel(@NotNull Application application) {
        super(application);
        jobOrder = new MutableLiveData<>();
        isLoading = new MutableLiveData<>(false);
        errorMessage = new MutableLiveData<>();

        jobOrderBoundService = new JobOrderBoundService();
    }

    public LiveData<JobOrderModel> getJobOrder() {
        return jobOrder;
    }

    public LiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    public LiveData<String> getErrorMessage() {
        return errorMessage;
    }
}
