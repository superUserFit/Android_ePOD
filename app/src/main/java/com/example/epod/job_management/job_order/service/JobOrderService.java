package com.example.epod.job_management.job_order.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.example.epod.auth.service.AuthService;
import com.example.epod.job_management.job_order.view.interfaces.JobOrderAdapterInterface;
import com.example.epod.job_management.job_order.data.repository.JobOrderCallback;
import com.example.epod.job_management.job_order.data.repository.JobOrderRepository;
import com.example.epod.job_management.job_order.data.repository.JobOrderAPI;
import com.example.epod.job_management.job_order.data.model.JobOrder;
import com.example.epod.job_management.job_order.data.model.JobOrderHasDetails;
import com.example.epod.utils.Request;

import java.util.List;

public class JobOrderService extends Service {
    private final IBinder binder = new LocalBinder();
    private JobOrderRepository jobOrderRepository;
    private final AuthService authService;

    private String sortOrder = "desc";
    private String selectedStatus = "All";

    @Override
    public void onCreate() {
        super.onCreate();

        jobOrderRepository = new JobOrderRepository(this, new JobOrderCallback() {
            @Override
            public void onLoadJobOrders(List<JobOrder> jobOrders) {

            }

            @Override
            public void onLoadJobOrder(JobOrder jobOrder) {

            }

            @Override
            public void onLoadJobOrderDetails(List<JobOrderHasDetails> jobOrderHasDetails) {

            }
        })
    }

    public void getJobOrderByUser(String sortOrder) {
        String authorization = authService.getAuthorization();
        jobOrderRepository.getJobOrderByUser(authorization, "createdAt", sortOrder);
    }

    public void getUpdateJobOrder(String jobOrderId) {
        String authorization = authService.getAuthorization();
        jobOrderRepository.getUpdateJobOrder(authorization, jobOrderId);
    }

    public void getUpdateJobOrderHasDetails(String jobOrderId) {
        String authorization = authService.getAuthorization();
        jobOrderRepository.getUpdateJobOrderHasDetails(authorization, jobOrderId);
    }

    public void toggleSortJobOrder() {
        sortOrder = sortOrder.equals("desc") ? "asc" : "desc";
        getJobOrderByUser();
    }

    public void setSelectedStatusIndex(int index) {
        switch (index) {
            case 0:
                selectedStatus = "All";
                break;

            case 1:
                selectedStatus = "Unassigned";
                break;

            case 2:
                selectedStatus = "Assigned";
                break;

            case 3:
                selectedStatus = "Started";
                break;

            case 4:
                selectedStatus = "Progressing";
                break;

            case 5:
                selectedStatus = "In Transit";
                break;

            default:
                selectedStatus = "All";
                break;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() { super.onDestroy(); }

    public class LocalBinder extends Binder {
        public JobOrderService getService() { return JobOrderService.this; }
    }
}
