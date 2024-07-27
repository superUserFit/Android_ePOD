package com.example.epod.job_management.job_order.data.repository;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface JobOrderAPI {
    @FormUrlEncoded
    @POST("job_order/api/job-order/get-job-order-by-user")
    Call<JobOrderResponse> getJobOrderByUser(
            @Header("Authorization") String authorization,
            @Field("param[sort]") String sort,
            @Field("param[order]") String order
    );

    @GET("job_order/api/job-order/get-update-job-order-data?id=")
    Call<JobOrderResponse> getUpdateJobOrder(
            @Header("Authorization") String authorization,
            @Query("id") String jobOrderId
    );

    @GET("job_order/api/job-order/get-update-job-order-has-details-data?id=")
    Call<JobOrderResponse> getUpdateJobOrderHasDetails(
            @Header("Authorization") String authorization,
            @Query("id") String jobOrderId
    );
}
