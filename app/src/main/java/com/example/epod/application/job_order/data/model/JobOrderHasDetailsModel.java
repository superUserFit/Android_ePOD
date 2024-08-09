package com.example.epod.application.job_order.data.model;

public class JobOrderHasDetailsModel {
    private String UUID, jobOrder, item, itemCode, itemType, itemGroupCode, itemCategoryCode;
    private String description, quantity, itemUOM;

    public JobOrderHasDetailsModel(
            String UUID, String jobOrder,
            String item, String itemCode, String itemType,
            String itemGroupCode, String itemCategoryCode, String description,
            String quantity, String itemUOM
    )
    {
        this.UUID = UUID;
        this.jobOrder = jobOrder;
        this.item = item;
        this.itemCode = itemCode;
        this.itemType = itemType;
        this.itemGroupCode = itemGroupCode;
        this.itemCategoryCode = itemCategoryCode;
        this.description = description;
        this.quantity = quantity;
        this.itemUOM = itemUOM;
    }

    public String getUUID() { return UUID; }
    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    public String getJobOrder() {
        return jobOrder;
    }

    public void setJobOrder(String jobOrder) {
        this.jobOrder = jobOrder;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getItemGroupCode() {
        return itemGroupCode;
    }

    public void setItemGroupCode(String itemGroupCode) {
        this.itemGroupCode = itemGroupCode;
    }

    public String getItemCategoryCode() {
        return itemCategoryCode;
    }

    public void setItemCategoryCode(String itemCategoryCode) {
        this.itemCategoryCode = itemCategoryCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getItemUOM() {
        return itemUOM;
    }

    public void setItemUOM(String itemUOM) {
        this.itemUOM = itemUOM;
    }
}
