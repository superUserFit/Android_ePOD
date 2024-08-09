package com.example.epod.job_management.job_order.data.model;

public class JobOrderModel {
    private String UUID, docNo, docDate, customerName, status;
    private String pickupLocation, pickupAddress, deliveryLocation, deliveryAddress;
    private String pickupContact, pickupContactNo, deliveryContact, deliveryContactNo;
    private String startPickupAt, endPickupAt, deadline;
    private String description, tripType, openTask, attentionName, phoneNo;
    private String address, containerNo, containerSize, containerType;


    public JobOrderModel(
            String UUID, String docNo, String docDate,
            String customerName, String status, String pickupLocation,
            String pickupAddress, String deliveryLocation, String deliveryAddress,
            String pickupContact, String pickupContactNo, String deliveryContact,
            String deliveryContactNo, String startPickupAt, String endPickupAt,
            String description, String deadline, String tripType, String openTask,
            String attentionName, String phoneNo, String address, String containerNo,
            String containerSize, String containerType
    ) {
        this.UUID = UUID;
        this.docNo = docNo;
        this.docDate = docDate;
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
        this.startPickupAt = startPickupAt;
        this.endPickupAt = endPickupAt;
        this.startPickupAt = startPickupAt;
        this.endPickupAt = endPickupAt;
        this.description = description;
        this.deadline = deadline;
        this.tripType = tripType;
        this.openTask = openTask;
        this.attentionName = attentionName;
        this.phoneNo = phoneNo;
        this.address = address;
        this.containerNo = containerNo;
        this.containerSize = containerSize;
        this.containerType = containerType;
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
        return docDate;
    }

    public void setDocDate(String docDate) {
        this.docDate = docDate;
    }

    public String getCustomerName() {
        return customerName;
    }

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
        return startPickupAt;
    }
    public void setStartPickupAt(String startPickupAt) {
        this.startPickupAt = startPickupAt;
    }

    public String getEndPickupAt() { return endPickupAt; }
    public void setEndPickupAt(String endPickupAt) {
        this.endPickupAt = endPickupAt;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getAttentionName() {
        return attentionName;
    }

    public void setAttentionName(String attentionName) {
        this.attentionName = attentionName;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContainerNo() {
        return containerNo;
    }

    public void setContainerNo(String containerNo) {
        this.containerNo = containerNo;
    }

    public String getContainerSize() {
        return containerSize;
    }

    public void setContainerSize(String containerSize) {
        this.containerSize = containerSize;
    }

    public String getContainerType() {
        return containerType;
    }

    public void setContainerType(String containerType) {
        this.containerType = containerType;
    }
}
