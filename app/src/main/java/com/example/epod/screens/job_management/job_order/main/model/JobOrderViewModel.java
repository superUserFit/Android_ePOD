package com.example.epod.screens.job_management.job_order.main.model;


import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.example.epod.application.job_order.data.model.JobOrderModel;
import com.example.epod.application.job_order.domain.repository.JobOrderRepository;
import com.example.epod.utils.helpers.ViewHelper;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


public class JobOrderViewModel extends AndroidViewModel {
    private final LiveData<List<JobOrderModel>> jobOrders;
    private final LiveData<ViewHelper.LoadingState> loadingState;
    private MutableLiveData<List<JobOrderModel>> filteredJobOrders = new MutableLiveData<>();
    private final MutableLiveData<String> selectedJobOrderStatus = new MutableLiveData<>();

    private String sortOrder = "desc";

    private final JobOrderRepository jobOrderRepository;


    public JobOrderViewModel(@NotNull Application application) {
        super(application);
        this.jobOrderRepository = new JobOrderRepository(application.getApplicationContext());
        this.jobOrders = jobOrderRepository.getJobOrders();
        this.loadingState = jobOrderRepository.getLoadingState();
        this.selectedJobOrderStatus.setValue("All");

        this.jobOrders.observeForever(new Observer<List<JobOrderModel>>() {
            @Override
            public void onChanged(List<JobOrderModel> jobOrderModels) {

            }
        });
    }

    public LiveData<List<JobOrderModel>> getJobOrders() {
        return jobOrders;
    }
    public LiveData<List<JobOrderModel>> getFilteredJobOrders() { return filteredJobOrders; }
    public LiveData<ViewHelper.LoadingState> getLoadingState() {
        return loadingState;
    }
    public LiveData<String> getSelectedJobOrderStatus() { return selectedJobOrderStatus; }

    public void toggleSortJobOrders() {
        sortOrder = sortOrder.equals("asc") ? "desc" : "asc";
    }

    public void loadJobOrders() {
        jobOrderRepository.getJobOrderByUser(sortOrder);
    }

    public void filterJobOrders(String selectedStatus) {
        selectedJobOrderStatus.setValue(selectedStatus);
        List<JobOrderModel> currentJobOrders = jobOrders.getValue();
        if(currentJobOrders != null) {
            List<JobOrderModel> filteredList = filterByStatus(currentJobOrders, selectedStatus);
            filteredJobOrders.setValue(filteredList);
        }
    }


    private List<JobOrderModel> filterByStatus(List<JobOrderModel> jobOrders, String status) {
        List<JobOrderModel> filteredList = new ArrayList<>();
        for(JobOrderModel jobOrder : jobOrders) {
            if(status.equals("All") || jobOrder.getStatus().equals(status)) {
                filteredList.add(jobOrder);
            }
        }

        return filteredList;
    }
}
