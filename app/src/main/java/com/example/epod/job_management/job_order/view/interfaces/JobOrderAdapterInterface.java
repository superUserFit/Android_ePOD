package com.example.epod.job_management.job_order.view.interfaces;

import com.example.epod.job_management.job_order.data.model.JobOrderModel;
import com.example.epod.job_management.job_order.data.model.JobOrderHasDetailsModel;

import java.util.List;

public interface JobOrderAdapterInterface {
    void setJobOrders(List<JobOrderModel> jobOrderModels);
    void setJobOrderHasDetails(List<JobOrderHasDetailsModel> jobOrderHasDetailModels);
}
