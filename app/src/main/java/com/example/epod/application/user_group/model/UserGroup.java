package com.example.epod.application.user_group.model;

public class UserGroup {
    private String UUID, userGroup;

    public UserGroup(String UUID, String userGroup) {
        this.UUID = UUID;
        this.userGroup = userGroup;
    }

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    public String getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(String userGroup) {
        this.userGroup = userGroup;
    }
}
