package com.example.epod.application.job_order.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.epod.application.job_order.data.repository.JobOrderCallback;
import com.example.epod.application.job_order.data.repository.JobOrderRepository;
import com.example.epod.application.job_order.data.model.JobOrderModel;
import com.example.epod.application.job_order.data.model.JobOrderHasDetailsModel;
import com.example.epod.utils.helpers.ViewHelper;

import java.util.List;

public class JobOrderService extends Service {
    private final IBinder binder = new LocalBinder();
    private JobOrderRepository jobOrderRepository;

    @Override
    public void onCreate() {
        super.onCreate();

        jobOrderRepository = new JobOrderRepository(this, new JobOrderCallback() {
            @Override
            public void onLoadJobOrders(List<JobOrderModel> jobOrderModels) {

            }

            @Override
            public void onLoadJobOrder(JobOrderModel jobOrderModel) {

            }

            @Override
            public void onLoadJobOrderDetails(List<JobOrderHasDetailsModel> jobOrderHasDetailModels) {

            }
        });
    }

    public void getJobOrderByUser(String sortOrder, JobOrderCallback jobOrderCallback) {
        jobOrderRepository.getJobOrderByUser("createdAt", sortOrder, new JobOrderCallback() {
            @Override
            public void onLoadJobOrders(List<JobOrderModel> jobOrderModels) {
                jobOrderCallback.onLoadJobOrders(jobOrderModels);
            }

            @Override
            public void onLoadJobOrder(JobOrderModel jobOrderModel) {

            }

            @Override
            public void onLoadJobOrderDetails(List<JobOrderHasDetailsModel> jobOrderHasDetailModels) {

            }
        });
    }

    public void getUpdateJobOrder(String jobOrderId, JobOrderCallback jobOrderCallback) {
        jobOrderRepository.getUpdateJobOrder(jobOrderId, new JobOrderCallback() {
            @Override
            public void onLoadJobOrders(List<JobOrderModel> jobOrderModels) {

            }

            @Override
            public void onLoadJobOrder(JobOrderModel jobOrderModel) {
                jobOrderCallback.onLoadJobOrder(jobOrderModel);
            }

            @Override
            public void onLoadJobOrderDetails(List<JobOrderHasDetailsModel> jobOrderHasDetailModels) {

            }
        });
    }

    public void getUpdateJobOrderHasDetails(String jobOrderId, JobOrderCallback jobOrderCallback) {
        jobOrderRepository.getUpdateJobOrderHasDetails(jobOrderId, new JobOrderCallback() {
            @Override
            public void onLoadJobOrders(List<JobOrderModel> jobOrderModels) {

            }

            @Override
            public void onLoadJobOrder(JobOrderModel jobOrderModel) {

            }

            @Override
            public void onLoadJobOrderDetails(List<JobOrderHasDetailsModel> jobOrderHasDetailModels) {
                jobOrderCallback.onLoadJobOrderDetails(jobOrderHasDetailModels);
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
