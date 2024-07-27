package com.example.epod.job_management.job_order.data.repository;

public interface JobOrderRepositoryInterface {
    /*
        Job Order
    */
    //  GET
    void getJobOrderByUser(String authorization, String sort, String order, JobOrderCallback jobOrderCallback);
    void getUpdateJobOrder(String authorization, String jobOrderId, JobOrderCallback jobOrderCallback);
    void getUpdateJobOrderHasDetails(String authorization, String jobOrderId, JobOrderCallback jobOrderCallback);
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
