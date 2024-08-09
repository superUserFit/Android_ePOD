package com.example.epod.application.job_order.data.repository;

public interface JobOrderRepositoryInterface {
    /*
        Job Order
    */
    //  GET
    void getJobOrderByUser(String sort, String order, JobOrderCallback jobOrderCallback);
    void getUpdateJobOrder(String jobOrderId, JobOrderCallback jobOrderCallback);
    void getUpdateJobOrderHasDetails(String jobOrderId, JobOrderCallback jobOrderCallback);
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
