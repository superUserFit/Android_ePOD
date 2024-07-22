package com.example.epod.job_management.job_order.controller;

import com.example.epod.job_management.job_order.view.model.JobOrder;
import com.example.epod.job_management.job_order.view.model.JobOrderHasDetails;

import java.util.List;

public interface JobOrderCallback {
    void onLoadJobOrders(List<JobOrder> jobOrders);
    void onLoadJobOrder(JobOrder jobOrder);
    void onLoadJobOrderDetails(List<JobOrderHasDetails> jobOrderHasDetails);
}
