package com.example.epod.job_management.job_order.interfaces;

import com.example.epod.job_management.job_order.view.model.JobOrder;
import com.example.epod.job_management.job_order.view.model.JobOrderHasDetails;

import java.util.List;

public interface JobOrderAdapterInterface {
    void setJobOrders(List<JobOrder> jobOrders);
    void setJobOrderHasDetails(List<JobOrderHasDetails> jobOrderHasDetails);
}
