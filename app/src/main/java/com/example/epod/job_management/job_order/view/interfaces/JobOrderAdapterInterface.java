package com.example.epod.job_management.job_order.view.interfaces;

import com.example.epod.job_management.job_order.data.model.JobOrder;
import com.example.epod.job_management.job_order.data.model.JobOrderHasDetails;

import java.util.List;

public interface JobOrderAdapterInterface {
    void setJobOrders(List<JobOrder> jobOrders);
    void setJobOrderHasDetails(List<JobOrderHasDetails> jobOrderHasDetails);
}
