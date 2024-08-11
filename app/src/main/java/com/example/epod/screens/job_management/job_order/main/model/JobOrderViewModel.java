package com.example.epod.screens.job_management.job_order.main.model;


import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.example.epod.application.job_order.data.model.JobOrderModel;
import com.example.epod.application.job_order.domain.repository.JobOrderRepository;
import com.example.epod.utils.helpers.ViewHelper;
import org.jetbrains.annotations.NotNull;

import java.util.List;


public class JobOrderViewModel extends AndroidViewModel {
    private final LiveData<List<JobOrderModel>> jobOrders;
    private final LiveData<ViewHelper.LoadingState> loadingState;

    private final JobOrderRepository jobOrderRepository;
    private String sortOrder = "desc";


    public JobOrderViewModel(@NotNull Application application) {
        super(application);
        this.jobOrderRepository = new JobOrderRepository(application.getApplicationContext());
        this.jobOrders = jobOrderRepository.getJobOrders();
        this.loadingState = jobOrderRepository.getLoadingState();
    }

    public LiveData<List<JobOrderModel>> getJobOrders() {
        return jobOrders;
    }

    public LiveData<ViewHelper.LoadingState> getLoadingState() {
        return loadingState;
    }

    public void toggleSortJobOrders() {
        sortOrder = sortOrder.equals("asc") ? "desc" : "asc";
    }

    public void loadJobOrders() {
        jobOrderRepository.getJobOrderByUser(sortOrder);
    }
}
