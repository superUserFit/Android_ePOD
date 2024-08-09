package com.example.epod.job_management.job_order.view.main.model;

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
import com.example.epod.job_management.job_order.data.model.JobOrder;
import com.example.epod.job_management.job_order.data.model.JobOrderHasDetails;
import com.example.epod.job_management.job_order.data.repository.JobOrderCallback;
import com.example.epod.job_management.job_order.service.JobOrderService;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class JobOrderViewModel extends AndroidViewModel {
    private final MutableLiveData<List<JobOrder>> jobOrders = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    private final MutableLiveData<String> errorMessage = new MutableLiveData<>();
    private String sortOrder = "desc";

    @SuppressLint("StaticFieldLeak")
    private JobOrderService jobOrderService;

    public JobOrderViewModel(@NotNull Application application) {
        super(application);
        bindJobOrderService(application);
    }

    private void bindJobOrderService(Context context) {
        Intent intent = new Intent(context, JobOrderService.class);
        context.startService(intent);
        context.bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
        Log.e("Bind", "Successfully bind the service");
    }

    private final ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            JobOrderService.LocalBinder binder = (JobOrderService.LocalBinder) service;
            jobOrderService = binder.getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            jobOrderService = null;
        }
    };

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

        if(jobOrderService != null) {
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
        } else {
            Log.e("ViewModel", "Service is null");
        }
    }

    public void toggleSortJobOrders() {
        sortOrder = sortOrder.equals("asc") ? "desc" : "asc";
        loadJobOrders(sortOrder);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        getApplication().unbindService(serviceConnection);
    }
}
