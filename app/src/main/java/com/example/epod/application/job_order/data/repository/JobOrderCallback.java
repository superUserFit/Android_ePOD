package com.example.epod.application.job_order.data.repository;

import com.example.epod.application.job_order.data.model.JobOrderHasDetailsModel;
import com.example.epod.application.job_order.data.model.JobOrderModel;

import java.util.List;

public interface JobOrderCallback {
    void onLoadJobOrders(List<JobOrderModel> jobOrderModels);
    void onLoadJobOrder(JobOrderModel jobOrderModel);
    void onLoadJobOrderDetails(List<JobOrderHasDetailsModel> jobOrderHasDetailModels);
}
