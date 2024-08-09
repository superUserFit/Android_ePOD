package com.example.epod.screens.job_management.job_order.main.model;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.epod.application.job_order.data.model.JobOrderModel;
import com.example.epod.application.job_order.data.model.JobOrderHasDetailsModel;
import com.example.epod.application.job_order.data.repository.JobOrderCallback;
import com.example.epod.application.job_order.service.JobOrderService;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class JobOrderViewModel extends AndroidViewModel {
    private final MutableLiveData<List<JobOrderModel>> jobOrders = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    private final MutableLiveData<String> errorMessage = new MutableLiveData<>();
    private String sortOrder = "desc";

    @SuppressLint("StaticFieldLeak")
    private JobOrderService jobOrderService;
    private boolean isServiceConnected = false;

    public JobOrderViewModel(@NotNull Application application) {
        super(application);
        bindJobOrderService(application);
    }

    private void bindJobOrderService(Context context) {
        Intent intent = new Intent(context, JobOrderService.class);
        context.startService(intent);
        context.bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    private final ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            JobOrderService.LocalBinder binder = (JobOrderService.LocalBinder) service;
            jobOrderService = binder.getService();

            if(jobOrderService != null) {
                isServiceConnected = true;
                loadJobOrders(sortOrder);
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            jobOrderService = null;
            isServiceConnected = false;
            errorMessage.setValue("Failed to connect to the service");
        }
    };

    public LiveData<List<JobOrderModel>> getJobOrders() {
        return jobOrders;
    }

    public LiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    public LiveData<String> getErrorMessage() {
        return errorMessage;
    }

    public void loadJobOrders(String sortOrder) {
        if(!isServiceConnected) {
            this.sortOrder = sortOrder;
            return;
        }

        isLoading.setValue(true);

        try {
            if(isServiceConnected) {
                jobOrderService.getJobOrderByUser(sortOrder, new JobOrderCallback() {
                    @Override
                    public void onLoadJobOrders(List<JobOrderModel> loadedJobOrderModels) {
                        jobOrders.setValue(loadedJobOrderModels);
                        isLoading.setValue(false);
                    }

                    @Override
                    public void onLoadJobOrder(JobOrderModel jobOrderModel) {

                    }

                    @Override
                    public void onLoadJobOrderDetails(List<JobOrderHasDetailsModel> jobOrderHasDetailModels) {

                    }
                });
            }
        } catch (Exception error) {
            isLoading.setValue(false);
            errorMessage.setValue(error.getMessage());
            Log.e("Error", error.getMessage());
        }
    }

    public void toggleSortJobOrders() {
        sortOrder = sortOrder.equals("asc") ? "desc" : "asc";
        loadJobOrders(sortOrder);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if(isServiceConnected) {
            getApplication().unbindService(serviceConnection);
        }
    }
}
