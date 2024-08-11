package com.example.epod.application.job_order.domain.repository;

public interface JobOrderRepositoryInterface {
    /*
        Job Order
    */
    //  GET
    void getJobOrderByUser(String order);
    void getUpdateJobOrder(String jobOrderId);
    void getUpdateJobOrderHasDetails(String jobOrderId);
    void getUpdateJobOrderHasSupervisors();
    void getUpdateJobOrderHasAssignmentData();

    //  CREATE
    void createJobOrderHasAssignment();

    //  UPDATE
    void updateJobOrderHasAssignment();
    void updateJobOrderHasAssignee();


    /*
        Job
    */
    //  GET
    void getJobOrderHasAssignmentByUser();
    void getUpdateJobOrderHasAssignment();
    void getUpdateJobOrderHasAssignee();

    //  UPDATE
    void acknowledgedJobOrderHasAssignment();
    void startJobOrderHasAssignment();
    void pickupJobOrderHasAssignment();
    void deliverJobOrderHasAssignment();
    void acceptJobOrderHasAssignment();
}
