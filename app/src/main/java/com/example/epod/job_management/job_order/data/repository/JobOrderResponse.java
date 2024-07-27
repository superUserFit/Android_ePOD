package com.example.epod.job_management.job_order.data.repository;

import com.example.epod.job_management.job_order.data.model.JobOrder;
import com.example.epod.job_management.job_order.data.model.JobOrderHasDetails;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class JobOrderResponse {
    @SerializedName("total")
    private int total;

    @SerializedName("rows")
    private List<JobOrder> jobOrders;
    private List<JobOrderHasDetails> jobOrderHasDetails;

    @SerializedName("data")
    private JobOrder jobOrder;

    public int getTotal() {
        return total;
    }

    public List<JobOrder> getJobOrders() {
        return jobOrders;
    }

    public JobOrder getJobOrder() { return jobOrder; }

    public List<JobOrderHasDetails> getJobOrderHasDetails() { return jobOrderHasDetails; }
}
