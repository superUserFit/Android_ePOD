package com.example.epod.job_management.job_order.data.repository;

import com.example.epod.job_management.job_order.data.model.JobOrderModel;
import com.example.epod.job_management.job_order.data.model.JobOrderHasDetailsModel;

import java.util.List;

public interface JobOrderCallback {
    void onLoadJobOrders(List<JobOrderModel> jobOrderModels);
    void onLoadJobOrder(JobOrderModel jobOrderModel);
    void onLoadJobOrderDetails(List<JobOrderHasDetailsModel> jobOrderHasDetailModels);
}
