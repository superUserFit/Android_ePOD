package com.example.epod.job_management.job_order.repository;

import android.util.Log;

import com.example.epod.job_management.job_order.view.model.JobOrder;
import com.example.epod.job_management.job_order.view.model.JobOrderHasDetails;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.Collections;
import java.util.List;

public class JobOrderRepository {
    private final JobOrderRepositoryInterface jobOrderRepositoryInterface;
    private final JobOrderCallback jobOrderCallback;

    public JobOrderRepository(
            JobOrderRepositoryInterface jobOrderRepositoryInterface,
            JobOrderCallback jobOrderCallback
    ) {
        this.jobOrderRepositoryInterface = jobOrderRepositoryInterface;
        this.jobOrderCallback = jobOrderCallback;
    }

    public void getJobOrderByUser(String authorization, String sort, String order) {
        Call<JobOrderResponse> call = jobOrderRepositoryInterface.getJobOrderByUser(authorization, sort, order);

        call.enqueue(new Callback<JobOrderResponse>() {
            @Override
            public void onResponse(Call<JobOrderResponse> call, Response<JobOrderResponse> response) {
                if (response.isSuccessful()) {
                    JobOrderResponse jobOrderResponse = response.body();
                    if(jobOrderResponse != null) {
                        List<JobOrder> jobOrders = jobOrderResponse.getJobOrders();
                        if(jobOrders == null) {
                            jobOrders = Collections.emptyList();
                        }
                        jobOrderCallback.onLoadJobOrders(jobOrders);
                    } else {
                        jobOrderCallback.onLoadJobOrders(Collections.emptyList());
                    }
                }
            }

            @Override
            public void onFailure(Call<JobOrderResponse> call, Throwable throwable) {
                Log.e("Error: ", throwable.getMessage());
                jobOrderCallback.onLoadJobOrders(Collections.emptyList());
            }
        });
    }

    public void getUpdateJobOrder(String authorization, String jobOrderId) {
        Call<JobOrderResponse> call = jobOrderRepositoryInterface.getUpdateJobOrder(authorization, jobOrderId);

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
                jobOrderCallback.onLoadJobOrder(null);
            }
        });
    }

    public void getUpdateJobOrderHasDetails(String authorization, String jobOrderId) {
        Call<JobOrderResponse> call = jobOrderRepositoryInterface.getUpdateJobOrderHasDetails(authorization, jobOrderId);

        call.enqueue(new Callback<JobOrderResponse>() {
            @Override
            public void onResponse(Call<JobOrderResponse> call, Response<JobOrderResponse> response) {
                if(response.isSuccessful()) {
                    JobOrderResponse jobOrderResponse = response.body();
                    if(jobOrderResponse != null) {
                        List<JobOrderHasDetails> jobOrderHasDetails = jobOrderResponse.getJobOrderHasDetails();
                        if(jobOrderHasDetails == null) {
                            jobOrderHasDetails = Collections.emptyList();
                        }
                        jobOrderCallback.onLoadJobOrderDetails(jobOrderHasDetails);
                    }
                }
            }

            @Override
            public void onFailure(Call<JobOrderResponse> call, Throwable throwable) {
                jobOrderCallback.onLoadJobOrderDetails(Collections.emptyList());
            }
        });
    }
}
