package com.example.epod.screens.job_management.job.view.model;

public class Job {
    private String UUID, customerName, jobStatus, jobType, jobOrder;
    private String pickupLocation, pickupAddress, deliveryLocation, deliveryAddress;
    private String pickupContact, pickupContactNo, deliveryContact, deliveryContactNo;
    private String startPickupAt, endPickupAt;

    public Job(
            String UUID, String customerName, String jobStatus,
            String jobType, String jobOrder, String pickupLocation,
            String pickupAddress, String deliveryLocation, String deliveryAddress,
            String pickupContact, String pickupContactNo, String deliveryContact,
            String deliveryContactNo, String startPickupAt, String endPickupAt
    ) {
        this.UUID = UUID;
        this.customerName = customerName;
        this.jobStatus = jobStatus;
        this.jobType = jobType;
        this.jobOrder = jobOrder;
        this.pickupLocation = pickupLocation;
        this.pickupAddress = pickupAddress;
        this.deliveryLocation = deliveryLocation;
        this.deliveryAddress = deliveryAddress;
        this.pickupContact = pickupContact;
        this.pickupContactNo = pickupContactNo;
        this.deliveryContact = deliveryContact;
        this.deliveryContactNo = deliveryContactNo;
        this.startPickupAt = startPickupAt;
        this.endPickupAt = endPickupAt;
    }

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(String jobStatus) {
        this.jobStatus = jobStatus;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public String getJobOrder() {
        return jobOrder;
    }

    public void setJobOrder(String jobOrder) {
        this.jobOrder = jobOrder;
    }

    public String getPickupLocation() {
        return pickupLocation;
    }

    public void setPickupLocation(String pickupLocation) {
        this.pickupLocation = pickupLocation;
    }

    public String getPickupAddress() {
        return pickupAddress;
    }

    public void setPickupAddress(String pickupAddress) {
        this.pickupAddress = pickupAddress;
    }

    public String getDeliveryLocation() {
        return deliveryLocation;
    }

    public void setDeliveryLocation(String deliveryLocation) {
        this.deliveryLocation = deliveryLocation;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getPickupContact() {
        return pickupContact;
    }

    public void setPickupContact(String pickupContact) {
        this.pickupContact = pickupContact;
    }

    public String getPickupContactNo() {
        return pickupContactNo;
    }

    public void setPickupContactNo(String pickupContactNo) {
        this.pickupContactNo = pickupContactNo;
    }

    public String getDeliveryContact() {
        return deliveryContact;
    }

    public void setDeliveryContact(String deliveryContact) {
        this.deliveryContact = deliveryContact;
    }

    public String getDeliveryContactNo() {
        return deliveryContactNo;
    }

    public void setDeliveryContactNo(String deliveryContactNo) {
        this.deliveryContactNo = deliveryContactNo;
    }

    public String getStartPickupAt() {
        return startPickupAt;
    }

    public void setStartPickupAt(String startPickupAt) {
        this.startPickupAt = startPickupAt;
    }

    public String getEndPickupAt() {
        return endPickupAt;
    }

    public void setEndPickupAt(String endPickupAt) {
        this.endPickupAt = endPickupAt;
    }
}
