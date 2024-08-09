package com.example.epod.job_management.job_order.data.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "jobOrderHasDetails")
public class JobOrderHasDetailsEntity {
    @PrimaryKey()
    @ColumnInfo(name = "UUID")
    private String UUID;

    @ColumnInfo(name = "jobOrder")
    private String jobOrder;
    @ColumnInfo(name = "item")
    private String item;
    @ColumnInfo(name = "itemCode")
    private String itemCode;
    @ColumnInfo(name = "itemType")
    private String itemType;

    @ColumnInfo(name = "itemGroupCode")
    private String itemGroupCode;
    @ColumnInfo(name = "itemCategoryCode")
    private String itemCategoryCode;
    @ColumnInfo(name = "itemUOM")
    private String itemUOM;
    @ColumnInfo(name = "description")
    private String description;
    @ColumnInfo(name = "quantity")
    private String quantity;
}
