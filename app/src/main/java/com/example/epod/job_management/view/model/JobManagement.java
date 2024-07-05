package com.example.epod.job_management.view.model;

public class JobManagement {
    String cardTitle;
    int icon;

    public JobManagement(String cardTitle, int icon) {
        this.cardTitle = cardTitle;
        this.icon = icon;
    }

    public String getCardTitle() {
        return cardTitle;
    }

    public void setCardTitle(String cardTitle) {
        this.cardTitle = cardTitle;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}
