package com.example.epod.job_management.job_order.controller;

import com.example.epod.job_management.job_order.view.model.JobOrder;

import java.util.List;

public interface DataLoadCallback {
    void onLoad(List<JobOrder> jobOrders);
    void onLoad(JobOrder jobOrder);
}
