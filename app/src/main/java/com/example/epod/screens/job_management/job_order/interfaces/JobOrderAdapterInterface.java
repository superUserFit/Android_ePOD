package com.example.epod.screens.job_management.job_order.interfaces;

import com.example.epod.application.job_order.data.model.JobOrderModel;
import com.example.epod.application.job_order.data.model.JobOrderHasDetailsModel;

import java.util.List;

public interface JobOrderAdapterInterface {
    void setJobOrders(List<JobOrderModel> jobOrderModels);
    void setJobOrderHasDetails(List<JobOrderHasDetailsModel> jobOrderHasDetailModels);
}
