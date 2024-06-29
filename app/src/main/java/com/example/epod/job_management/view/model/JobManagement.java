package com.example.epod.job_management.view.model;

public class JobManagement {
    int icon;
    String cardTitle;

    public JobManagement(int icon, String cardTitle) {
        this.icon = icon;
        this.cardTitle = cardTitle;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getCardTitle() {
        return cardTitle;
    }

    public void setCardTitle(String cardTitle) {
        this.cardTitle = cardTitle;
    }
}
