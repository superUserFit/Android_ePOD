package com.example.epod.application.job_order.domain.repository.api;

import com.example.epod.application.job_order.data.model.JobOrderModel;
import com.example.epod.application.job_order.data.model.JobOrderHasDetailsModel;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class JobOrderResponse {
    @SerializedName("total")
    private int total;

    @SerializedName("rows")
    private List<JobOrderModel> jobOrderModels;
    private List<JobOrderHasDetailsModel> jobOrderHasDetailModels;

    @SerializedName("data")
    private JobOrderModel jobOrderModel;

    public int getTotal() {
        return total;
    }

    public List<JobOrderModel> getJobOrders() {
        return jobOrderModels;
    }

    public JobOrderModel getJobOrder() { return jobOrderModel; }

    public List<JobOrderHasDetailsModel> getJobOrderHasDetails() { return jobOrderHasDetailModels; }
}
