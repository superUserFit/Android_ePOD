package com.example.epod.application.company.data.model;

public class Company {
    private String UUID, company;

    public Company(String UUID, String company) {
        this.UUID = UUID;
        this.company = company;
    }

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
