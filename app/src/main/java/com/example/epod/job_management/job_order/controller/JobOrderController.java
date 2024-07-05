package com.example.epod.job_management.job_order.controller;

import android.util.Log;

import com.example.epod.job_management.job_order.interfaces.JobOrderAdapterInterface;
import com.example.epod.job_management.job_order.interfaces.JobOrderInterface;
import com.example.epod.job_management.job_order.view.model.JobOrder;
import com.example.epod.job_management.job_order.view.model.JobOrderHasDetails;
import com.example.epod.utils.Request;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

public class JobOrderController {
    private final JobOrderAdapterInterface jobOrderAdapter;
    private final JobOrderInterface jobOrderInterface = Request.getRetrofitInstance().create(JobOrderInterface.class);
    private final DataLoadCallback dataLoadCallback;

    public JobOrderController(JobOrderAdapterInterface jobOrderAdapter, DataLoadCallback dataLoadCallback) {
        this.jobOrderAdapter = jobOrderAdapter;
        this.dataLoadCallback = dataLoadCallback;
    }

    public void getAllJobOrderHasAssignment(String jobOrderStatus) {
        Call<JobOrderResponse> call = jobOrderInterface.getAllJobOrderHasAssignment(jobOrderStatus, "jobOrderStatus", "docDate", "desc");

        call.enqueue(new Callback<JobOrderResponse>() {
            @Override
            public void onResponse(Call<JobOrderResponse> call, Response<JobOrderResponse> response) {
                if (response.isSuccessful()) {
                    JobOrderResponse jobOrderResponse = response.body();
                    if (jobOrderResponse != null) {
                        List<JobOrder> jobOrders = jobOrderResponse.getJobOrders();
                        jobOrderAdapter.setJobOrders(jobOrders);
                        dataLoadCallback.onLoad(jobOrders);
                    }
                }
            }

            @Override
            public void onFailure(Call<JobOrderResponse> call, Throwable throwable) {
                Log.e("Error: ", throwable.getMessage());
            }
        });
    }

    public void getUpdateJobOrder(String jobOrderId) {
        Call<JobOrderResponse> call = jobOrderInterface.getUpdateJobOrder(jobOrderId);

        call.enqueue(new Callback<JobOrderResponse>() {
            @Override
            public void onResponse(Call<JobOrderResponse> call, Response<JobOrderResponse> response) {
                if(response.isSuccessful()) {
                    JobOrderResponse jobOrderResponse = response.body();
                    if(jobOrderResponse != null) {
                        JobOrder jobOrder = jobOrderResponse.getJobOrder();
                        jobOrderAdapter.setJobOrder(jobOrder);
                    }
                }
            }

            @Override
            public void onFailure(Call<JobOrderResponse> call, Throwable throwable) {
                Log.e("Error", throwable.getMessage());
            }
        });
    }

    public void getUpdateJobOrderHasDetails(String jobOrderId) {
        Call<JobOrderResponse> call = jobOrderInterface.getUpdateJobOrderHasDetails(jobOrderId);

        call.enqueue(new Callback<JobOrderResponse>() {
            @Override
            public void onResponse(Call<JobOrderResponse> call, Response<JobOrderResponse> response) {
                if(response.isSuccessful()) {
                    JobOrderResponse jobOrderResponse = response.body();
                    if(jobOrderResponse != null) {
                        JobOrderHasDetails jobOrderHasDetails = (JobOrderHasDetails) jobOrderResponse.getJobOrderHasDetails();
                        jobOrderAdapter.setJobOrderHasDetails((List<JobOrderHasDetails>) jobOrderHasDetails);
                    }
                }
            }

            @Override
            public void onFailure(Call<JobOrderResponse> call, Throwable throwable) {

            }
        });
    }
}
