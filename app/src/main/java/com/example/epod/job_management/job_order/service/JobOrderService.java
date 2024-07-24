package com.example.epod.job_management.job_order.service;

import android.content.Context;
import android.util.Log;

import com.example.epod.auth.service.AuthService;
import com.example.epod.job_management.job_order.interfaces.JobOrderAdapterInterface;
import com.example.epod.job_management.job_order.repository.JobOrderCallback;
import com.example.epod.job_management.job_order.repository.JobOrderRepository;
import com.example.epod.job_management.job_order.repository.JobOrderRepositoryInterface;
import com.example.epod.job_management.job_order.view.model.JobOrder;
import com.example.epod.job_management.job_order.view.model.JobOrderHasDetails;
import com.example.epod.utils.Request;

import java.util.List;

public class JobOrderService {
    private final JobOrderRepository jobOrderRepository;
    private final AuthService authService;

    private String sortOrder = "desc";
    private String selectedStatus = "All";

    public JobOrderService(
            Context context,
            JobOrderAdapterInterface jobOrderAdapterInterface,
            AuthService authService
            ) {
        JobOrderRepositoryInterface jobOrderRepositoryInterface = Request.getRetrofitInstance(context).create(JobOrderRepositoryInterface.class);

        this.authService = authService;

        this.jobOrderRepository = new JobOrderRepository(
                jobOrderRepositoryInterface,
                new JobOrderCallback() {
                    @Override
                    public void onLoadJobOrders(List<JobOrder> jobOrders) {
                        jobOrderAdapterInterface.setJobOrders(jobOrders);
                        ((JobOrderCallback) context).onLoadJobOrders(jobOrders);
                    }

                    @Override
                    public void onLoadJobOrder(JobOrder jobOrder) {

                    }

                    @Override
                    public void onLoadJobOrderDetails(List<JobOrderHasDetails> jobOrderHasDetails) {
                        jobOrderAdapterInterface.setJobOrderHasDetails(jobOrderHasDetails);
                        ((JobOrderCallback) context).onLoadJobOrderDetails(jobOrderHasDetails);
                    }
                }
        );
    }

    public void getJobOrderByUser() {
        String authorization = authService.getAuthorization();
        jobOrderRepository.getJobOrderByUser(authorization, "createdAt", sortOrder);
    }

    public void getUpdateJobOrder(String jobOrderId) {
        String authorization = authService.getAuthorization();
        jobOrderRepository.getUpdateJobOrder(authorization, jobOrderId);
    }

    public void getUpdateJobOrderHasDetails(String jobOrderId) {
        String authorization = authService.getAuthorization();
        jobOrderRepository.getUpdateJobOrderHasDetails(authorization, jobOrderId);
    }

    public void toggleSortJobOrder() {
        sortOrder = sortOrder.equals("desc") ? "asc" : "desc";
        getJobOrderByUser();
    }

    public void setSelectedStatusIndex(int index) {
        switch (index) {
            case 0:
                selectedStatus = "All";
                break;

            case 1:
                selectedStatus = "Unassigned";
                break;

            case 2:
                selectedStatus = "Assigned";
                break;

            case 3:
                selectedStatus = "Started";
                break;

            case 4:
                selectedStatus = "Progressing";
                break;

            case 5:
                selectedStatus = "In Transit";
                break;

            default:
                selectedStatus = "All";
                break;
        }
    }
}
