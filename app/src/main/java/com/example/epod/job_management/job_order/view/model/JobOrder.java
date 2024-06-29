package com.example.epod.job_management.job_order.view.model;

public class JobOrder {
    private String title;
    private String description;
    private int imageResId;

    public JobOrder(String title, String description, int imageResId) {
        this.title = title;
        this.description = description;
        this.imageResId = imageResId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getImageResId() {
        return imageResId;
    }
}
