package com.example.epod.utils.helpers;

import android.content.Context;
import android.content.SharedPreferences;

public class RepositoryHelper {
    private static final String PREFERENCES_NAME = "auth";
    private static final String KEY_TOKEN = "token";
    private static final String KEY_USER_ID = "userId";
    private static final String KEY_AUTHORIZATION = "authorization";
    private static final String KEY_ACCOUNT = "account";
    private static final String KEY_COMPANY = "company";
    private static final String KEY_LOCATION = "location";
    private static final String KEY_USER_GROUP = "user_group";
    private static final String KEY_USER_ROLE = "user_role";

    private SharedPreferences sharedPreferences;

    public RepositoryHelper(Context context) {
        sharedPreferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    public String getAuthorization() {
        return sharedPreferences.getString(KEY_AUTHORIZATION, null);
    }
}
