package com.example.epod.job_management.job_order.data.repository;

import android.content.Context;
import android.util.Log;

import com.example.epod.job_management.job_order.data.model.JobOrder;
import com.example.epod.job_management.job_order.data.model.JobOrderHasDetails;
import com.example.epod.utils.Request;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.Collections;
import java.util.List;

public class JobOrderRepository implements JobOrderRepositoryInterface {
    private final JobOrderAPI jobOrderApi;

    public JobOrderRepository(
            Context context,
            JobOrderCallback jobOrderCallback
    ) {
        this.jobOrderApi = Request.getRetrofitInstance(context).create(JobOrderAPI.class);
    }

    @Override
    public void getJobOrderByUser(String sort, String order, String authorization, JobOrderCallback jobOrderCallback) {
        Call<JobOrderResponse> call = jobOrderApi.getJobOrderByUser(sort, order, authorization);

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

    @Override
    public void getUpdateJobOrder(String jobOrderId, String authorization, JobOrderCallback jobOrderCallback) {
        Call<JobOrderResponse> call = jobOrderApi.getUpdateJobOrder(jobOrderId, authorization);

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

    @Override
    public void getUpdateJobOrderHasDetails(String jobOrderId, String authorization, JobOrderCallback jobOrderCallback) {
        Call<JobOrderResponse> call = jobOrderApi.getUpdateJobOrderHasDetails(jobOrderId, authorization);

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

    @Override
    public void getUpdateJobOrderHasSupervisors() {

    }

    @Override
    public void getUpdateJobOrderHasAssignmentData() {

    }

    @Override
    public void createJobOrderHasAssignment() {

    }

    @Override
    public void updateJobOrderHasAssignment() {

    }

    @Override
    public void updateJobOrderHasAssignee() {

    }

    @Override
    public void getJobOrderHasAssignmentByUser() {

    }

    @Override
    public void getUpdateJobOrderHasAssignment() {

    }

    @Override
    public void getUpdateJobOrderHasAssignee() {

    }

    @Override
    public void acknowledgedJobOrderHasAssignment() {

    }

    @Override
    public void startJobOrderHasAssignment() {

    }

    @Override
    public void pickupJobOrderHasAssignment() {

    }

    @Override
    public void deliverJobOrderHasAssignment() {

    }

    @Override
    public void acceptJobOrderHasAssignment() {

    }
}
