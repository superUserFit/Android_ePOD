package com.example.epod.job_management.job_order.view.details.model;

import android.annotation.SuppressLint;
import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.epod.job_management.job_order.data.model.JobOrder;
import com.example.epod.job_management.job_order.data.model.JobOrderHasDetails;
import com.example.epod.job_management.job_order.data.repository.JobOrderCallback;
import com.example.epod.job_management.job_order.service.JobOrderService;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class JobOrderDetailsViewModel extends AndroidViewModel {
    @SuppressLint("StaticFieldLeak")
    private final JobOrderService jobOrderService;

    private final MutableLiveData<JobOrder> jobOrder;
    private final MutableLiveData<Boolean> isLoading;
    private final MutableLiveData<String> errorMessage;

    public JobOrderDetailsViewModel(@NotNull Application application) {
        super(application);
        jobOrder = new MutableLiveData<>();
        isLoading = new MutableLiveData<>(false);
        errorMessage = new MutableLiveData<>();

        jobOrderService = new JobOrderService();
    }

    public LiveData<JobOrder> getJobOrder() {
        return jobOrder;
    }

    public LiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    public LiveData<String> getErrorMessage() {
        return errorMessage;
    }

    public void loadJobOrder(String jobOrderId) {
        isLoading.setValue(true);
        jobOrderService.getUpdateJobOrder(jobOrderId, new JobOrderCallback() {
            @Override
            public void onLoadJobOrders(List<JobOrder> jobOrders) {

            }

            @Override
            public void onLoadJobOrder(JobOrder loadedJobOrder) {
                jobOrder.setValue(loadedJobOrder);
                isLoading.setValue(false);
            }

            @Override
            public void onLoadJobOrderDetails(List<JobOrderHasDetails> jobOrderHasDetails) {

            }
        });
    }
}
