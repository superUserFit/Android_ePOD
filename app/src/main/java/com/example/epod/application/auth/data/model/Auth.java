package com.example.epod.application.auth.data.model;

import com.example.epod.application.account.model.Account;
import com.example.epod.application.company.data.model.Company;
import com.example.epod.application.location.data.model.Location;
import com.example.epod.application.user_group.model.UserGroup;

public class Auth {
    private String id, username, access_token, Authorization;
    private Account currentAccount;
    private Company currentCompany;
    private Location currentLocation;
    private UserGroup currentUserGroup;


    public Auth(
            String id,
            String username,
            String access_token,
            String Authorization,
            Account currentAccount,
            Company currentCompany,
            Location currentLocation,
            UserGroup currentUserGroup

    ) {
        this.id = id;
        this.username = username;
        this.access_token = access_token;
        this.Authorization = Authorization;
        this.currentAccount = currentAccount;
        this.currentCompany = currentCompany;
        this.currentLocation = currentLocation;
        this.currentUserGroup = currentUserGroup;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) { this.id = id; }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getAuthorization() { return Authorization; }

    public void setAuthorization(String authorization) { this.Authorization = authorization; }

    public Account getCurrentAccount() { return currentAccount; };

    public void setCurrentAccount(Account currentAccount) { this.currentAccount = currentAccount; }

    public Company getCurrentCompany() { return currentCompany; };
    public void setCurrentCompany(Company currentCompany) {
        this.currentCompany = currentCompany;
    }

    public Location getCurrentLocation() { return currentLocation; };
    public void setCurrentLocation(Location currentLocation) {
        this.currentLocation = currentLocation;
    }

    public UserGroup getCurrentUserGroup() { return currentUserGroup; };
    public void setCurrentUserGroup(UserGroup currentUserGroup) {
        this.currentUserGroup = currentUserGroup;
    }
}
