package com.example.epod.job_management.job_order.data.repository;

import com.example.epod.job_management.job_order.data.model.JobOrder;
import com.example.epod.job_management.job_order.data.model.JobOrderHasDetails;

import java.util.List;

public interface JobOrderCallback {
    void onLoadJobOrders(List<JobOrder> jobOrders);
    void onLoadJobOrder(JobOrder jobOrder);
    void onLoadJobOrderDetails(List<JobOrderHasDetails> jobOrderHasDetails);
}
