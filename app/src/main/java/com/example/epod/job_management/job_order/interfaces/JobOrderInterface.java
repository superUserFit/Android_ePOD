package com.example.epod.job_management.job_order.interfaces;

import com.example.epod.job_management.job_order.controller.JobOrderResponse;
import com.example.epod.job_management.job_order.view.model.JobOrder;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface JobOrderInterface {
    @FormUrlEncoded
    @POST("job_order/api/job-order/get-all-job-order-has-assignment?jobStatus=")
    Call<JobOrderResponse> getAllJobOrderHasAssignment(
            @Query("jobStatus") String jobStatus,
            @Query("filterBy") String filterBy,
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
