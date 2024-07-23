package com.example.epod.job_management.job_order.controller;

import android.util.Log;

import com.example.epod.job_management.job_order.interfaces.JobOrderAdapterInterface;
import com.example.epod.job_management.job_order.interfaces.JobOrderRepository;
import com.example.epod.job_management.job_order.view.model.JobOrder;
import com.example.epod.job_management.job_order.view.model.JobOrderHasDetails;
import com.example.epod.utils.Request;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

public class JobOrderController {
    private final JobOrderAdapterInterface jobOrderAdapter;
    private final JobOrderRepository jobOrderRepository = Request.getRetrofitInstance().create(JobOrderRepository.class);
    private final JobOrderCallback jobOrderCallback;

    public JobOrderController(JobOrderAdapterInterface jobOrderAdapter, JobOrderCallback jobOrderCallback) {
        this.jobOrderAdapter = jobOrderAdapter;
        this.jobOrderCallback = jobOrderCallback;
    }

    public void getJobOrderByUser() {
        Call<JobOrderResponse> call = jobOrderRepository.getJobOrderByUser("docDate", "desc");

        call.enqueue(new Callback<JobOrderResponse>() {
            @Override
            public void onResponse(Call<JobOrderResponse> call, Response<JobOrderResponse> response) {
                if (response.isSuccessful()) {
                    JobOrderResponse jobOrderResponse = response.body();
                    if (jobOrderResponse != null) {
                        List<JobOrder> jobOrders = jobOrderResponse.getJobOrders();
                        jobOrderAdapter.setJobOrders(jobOrders);
                        jobOrderCallback.onLoadJobOrders(jobOrders);
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
        Call<JobOrderResponse> call = jobOrderRepository.getUpdateJobOrder(jobOrderId);

        call.enqueue(new Callback<JobOrderResponse>() {
            @Override
            public void onResponse(Call<JobOrderResponse> call, Response<JobOrderResponse> response) {
                if(response.isSuccessful()) {
                    JobOrderResponse jobOrderResponse = response.body();
                    if(jobOrderResponse != null) {
                        JobOrder jobOrder = jobOrderResponse.getJobOrder();
                        jobOrderCallback.onLoadJobOrder(jobOrder);
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
        Call<JobOrderResponse> call = jobOrderRepository.getUpdateJobOrderHasDetails(jobOrderId);

        call.enqueue(new Callback<JobOrderResponse>() {
            @Override
            public void onResponse(Call<JobOrderResponse> call, Response<JobOrderResponse> response) {
                if(response.isSuccessful()) {
                    JobOrderResponse jobOrderResponse = response.body();
                    if(jobOrderResponse != null) {
                        List<JobOrderHasDetails> jobOrderHasDetails = jobOrderResponse.getJobOrderHasDetails();
                        jobOrderAdapter.setJobOrderHasDetails(jobOrderHasDetails);
                        jobOrderCallback.onLoadJobOrderDetails(jobOrderHasDetails);
                    }
                }
            }

            @Override
            public void onFailure(Call<JobOrderResponse> call, Throwable throwable) {

            }
        });
    }
}
