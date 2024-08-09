package com.example.epod.application.account.model;

public class Account {
    private String UUID, account;

    public Account(String account) {
        this.account = account;
    }

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
}
