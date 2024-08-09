package com.example.epod.job_management.job_order.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.epod.job_management.job_order.data.repository.JobOrderCallback;
import com.example.epod.job_management.job_order.data.repository.JobOrderRepository;
import com.example.epod.job_management.job_order.data.model.JobOrder;
import com.example.epod.job_management.job_order.data.model.JobOrderHasDetails;
import com.example.epod.utils.helpers.ServiceHelper;

import java.util.List;

public class JobOrderService extends Service {
    private final IBinder binder = new LocalBinder();
    private JobOrderRepository jobOrderRepository;

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
        });
    }

    public void getJobOrderByUser(String sortOrder, JobOrderCallback jobOrderCallback) {
        String authorization = ServiceHelper.getInstance().getAuthorization();
        Log.e("Authorization", authorization);
        jobOrderRepository.getJobOrderByUser("createdAt", sortOrder, authorization, new JobOrderCallback() {
            @Override
            public void onLoadJobOrders(List<JobOrder> jobOrders) {
                jobOrderCallback.onLoadJobOrders(jobOrders);
            }

            @Override
            public void onLoadJobOrder(JobOrder jobOrder) {

            }

            @Override
            public void onLoadJobOrderDetails(List<JobOrderHasDetails> jobOrderHasDetails) {

            }
        });
    }

    public void getUpdateJobOrder(String jobOrderId, JobOrderCallback jobOrderCallback) {
        String authorization = ServiceHelper.getInstance().getAuthorization();
        jobOrderRepository.getUpdateJobOrder(jobOrderId, authorization, new JobOrderCallback() {
            @Override
            public void onLoadJobOrders(List<JobOrder> jobOrders) {

            }

            @Override
            public void onLoadJobOrder(JobOrder jobOrder) {
                jobOrderCallback.onLoadJobOrder(jobOrder);
            }

            @Override
            public void onLoadJobOrderDetails(List<JobOrderHasDetails> jobOrderHasDetails) {

            }
        });
    }

    public void getUpdateJobOrderHasDetails(String jobOrderId, JobOrderCallback jobOrderCallback) {
        String authorization = ServiceHelper.getInstance().getAuthorization();
        jobOrderRepository.getUpdateJobOrderHasDetails(jobOrderId, authorization, new JobOrderCallback() {
            @Override
            public void onLoadJobOrders(List<JobOrder> jobOrders) {

            }

            @Override
            public void onLoadJobOrder(JobOrder jobOrder) {

            }

            @Override
            public void onLoadJobOrderDetails(List<JobOrderHasDetails> jobOrderHasDetails) {
                jobOrderCallback.onLoadJobOrderDetails(jobOrderHasDetails);
            }
        });
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    @Override
    public void onDestroy() { super.onDestroy(); }

    public class LocalBinder extends Binder {
        public JobOrderService getService() { return JobOrderService.this; }
    }
}
