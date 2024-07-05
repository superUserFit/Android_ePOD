package com.example.epod.job_management.job_order.view.model;

public class JobOrder {
    private String UUID, docNo, docDateFormat, jobOrder, customerName, status;
    private String pickupLocation, pickupAddress, deliveryLocation, deliveryAddress;
    private String pickupContact, pickupContactNo, deliveryContact, deliveryContactNo;
    private String startPickupAtFormat, endPickupAtFormat;
    private String tripType, openTask;

    public JobOrder(
            String UUID, String docNo, String docDateFormat, String jobOrder,
            String customerName, String status, String pickupLocation,
            String pickupAddress, String deliveryLocation, String deliveryAddress,
            String pickupContact, String pickupContactNo, String deliveryContact,
            String deliveryContactNo, String startPickupAtFormat, String endPickupAtFormat,
            String tripType, String openTask
    ) {
        this.UUID = UUID;
        this.docNo = docNo;
        this.docDateFormat = docDateFormat;
        this.jobOrder = jobOrder;
        this.customerName = customerName;
        this.status = status;
        this.pickupLocation = pickupLocation;
        this.pickupAddress = pickupAddress;
        this.deliveryLocation = deliveryLocation;
        this.deliveryAddress = deliveryAddress;
        this.pickupContact = pickupContact;
        this.pickupContactNo = pickupContactNo;
        this.deliveryContact = deliveryContact;
        this.deliveryContactNo = deliveryContactNo;
        this.startPickupAtFormat = startPickupAtFormat;
        this.endPickupAtFormat = endPickupAtFormat;
        this.tripType = tripType;
        this.openTask = openTask;
    }

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    public String getDocNo() {
        return docNo;
    }

    public void setDocNo(String docNo) {
        this.docNo = docNo;
    }

    public String getDocDate() {
        return docDateFormat;
    }

    public void setDocDate(String docDateFormat) {
        this.docDateFormat = docDateFormat;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getJobOrder() { return jobOrder; }

    public void setJobOrder(String jobOrder) { this.jobOrder = jobOrder; }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
        return startPickupAtFormat;
    }

    public void setStartPickupAt(String startPickupAtFormat) {
        this.startPickupAtFormat = startPickupAtFormat;
    }

    public String getEndPickupAt() {
        return endPickupAtFormat;
    }

    public void setEndPickupAt(String endPickupAtFormat) {
        this.endPickupAtFormat = endPickupAtFormat;
    }

    public String getTripType() {
        return tripType;
    }

    public void setTripType(String tripType) {
        this.tripType = tripType;
    }

    public String getOpenTask() {
        return openTask;
    }

    public void setOpenTask(String openTask) {
        this.openTask = openTask;
    }
}
