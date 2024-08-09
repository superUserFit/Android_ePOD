package com.example.epod.job_management.job_order.data.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "jobOrder")
public class JobOrderEntity {
    @PrimaryKey()
    @ColumnInfo(name = "UUID")
    private String UUID;

    @ColumnInfo(name = "docNo")
    private String docNo;
    @ColumnInfo(name = "docDate")
    private String docDate;
    @ColumnInfo(name = "customerCode")
    private String customerCode;
    @ColumnInfo(name = "customerName")
    private String customerName;
    @ColumnInfo(name = "status")
    private String status;

    @ColumnInfo(name = "pickupLocation")
    private String pickupLocation;
    @ColumnInfo(name = "pickupAddress")
    private String pickupAddress;
    @ColumnInfo(name = "deliveryLocation")
    private String deliveryLocation;
    @ColumnInfo(name = "deliveryAddress")
    private String deliveryAddress;

    @ColumnInfo(name = "startPickupAt")
    private String startPickupAt;
    @ColumnInfo(name = "endPickupAt")
    private String endPickupAt;
    @ColumnInfo(name = "deadline")
    private String deadline;
    @ColumnInfo(name = "description")
    private String description;
    @ColumnInfo(name = "tripType")
    private String tripType;
    @ColumnInfo(name = "attentionName")
    private String attentionName;
    @ColumnInfo(name = "phoneNo")
    private String phoneNo;

    @ColumnInfo(name = "address")
    private String address;
    @ColumnInfo(name = "containerNo")
    private String containerNo;
    @ColumnInfo(name = "containerSize")
    private String containerSize;
    @ColumnInfo(name = "containerType")
    private String containerType;
}
