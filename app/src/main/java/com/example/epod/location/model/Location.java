package com.example.epod.location.model;

public class Location {
    private String UUID, locationCode;

    public Location(String UUID, String locationCode) {
        this.UUID = UUID;
        this.locationCode = locationCode;
    }

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    public String getLocationCode() {
        return locationCode;
    }

    public void setLocationCode(String locationCode) {
        this.locationCode = locationCode;
    }
}
