package com.example.epod.screens.job_management.job_order.details.model;

import android.annotation.SuppressLint;
import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.epod.application.job_order.data.model.JobOrderModel;
import com.example.epod.application.job_order.data.model.JobOrderHasDetailsModel;
import com.example.epod.application.job_order.data.repository.JobOrderCallback;
import com.example.epod.application.job_order.service.JobOrderService;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class JobOrderDetailsViewModel extends AndroidViewModel {
    @SuppressLint("StaticFieldLeak")
    private final JobOrderService jobOrderService;

    private final MutableLiveData<JobOrderModel> jobOrder;
    private final MutableLiveData<Boolean> isLoading;
    private final MutableLiveData<String> errorMessage;

    public JobOrderDetailsViewModel(@NotNull Application application) {
        super(application);
        jobOrder = new MutableLiveData<>();
        isLoading = new MutableLiveData<>(false);
        errorMessage = new MutableLiveData<>();

        jobOrderService = new JobOrderService();
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

    public void loadJobOrder(String jobOrderId) {
        isLoading.setValue(true);
        jobOrderService.getUpdateJobOrder(jobOrderId, new JobOrderCallback() {
            @Override
            public void onLoadJobOrders(List<JobOrderModel> jobOrderModels) {

            }

            @Override
            public void onLoadJobOrder(JobOrderModel loadedJobOrderModel) {
                jobOrder.setValue(loadedJobOrderModel);
                isLoading.setValue(false);
            }

            @Override
            public void onLoadJobOrderDetails(List<JobOrderHasDetailsModel> jobOrderHasDetailModels) {

            }
        });
    }
}
