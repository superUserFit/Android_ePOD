package com.example.epod.job_management.job_order.view.main.model;

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

public class JobOrderViewModel extends AndroidViewModel {
    @SuppressLint("StaticFieldLeak")
    private final JobOrderService jobOrderService;

    private final MutableLiveData<List<JobOrder>> jobOrders;
    private final MutableLiveData<Boolean> isLoading;
    private final MutableLiveData<String> errorMessage;

    private String sortOrder = "desc";

    public JobOrderViewModel(@NotNull Application application) {
        super(application);
        jobOrders = new MutableLiveData<>();
        isLoading = new MutableLiveData<>(false);
        errorMessage = new MutableLiveData<>();

        jobOrderService = new JobOrderService();
    }

    public LiveData<List<JobOrder>> getJobOrders() {
        return jobOrders;
    }

    public LiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    public LiveData<String> getErrorMessage() {
        return errorMessage;
    }

    public void loadJobOrders(String sortOrder) {
        isLoading.setValue(true);
        jobOrderService.getJobOrderByUser(sortOrder, new JobOrderCallback() {
            @Override
            public void onLoadJobOrders(List<JobOrder> loadedJobOrders) {
                jobOrders.setValue(loadedJobOrders);
                isLoading.setValue(false);
            }

            @Override
            public void onLoadJobOrder(JobOrder jobOrder) {

            }

            @Override
            public void onLoadJobOrderDetails(List<JobOrderHasDetails> jobOrderHasDetails) {

            }
        });
    }

    public void toggleSortJobOrders() {
        sortOrder = sortOrder.equals("asc") ? "desc" : "asc";
        loadJobOrders(sortOrder);
    }
}
