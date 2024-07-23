package com.example.epod.job_management.job_order.repository;

import com.example.epod.job_management.job_order.controller.JobOrderResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface JobOrderRepository {
    @FormUrlEncoded
    @POST("job_order/api/job-order/get-job-order-has-assignment")
    Call<JobOrderResponse> getJobOrderByUser(
            @Field("param[sort]") String sort,
            @Field("param[order]") String order
    );

    @GET("job_order/api/job-order/get-update-job-order-data?id=")
    Call<JobOrderResponse> getUpdateJobOrder(
            @Query("id") String jobOrderId
    );

    @GET("job_order/api/job-order/get-update-job-order-has-details-data?id=")
    Call<JobOrderResponse> getUpdateJobOrderHasDetails(
            @Query("id") String jobOrderId
    );
}
