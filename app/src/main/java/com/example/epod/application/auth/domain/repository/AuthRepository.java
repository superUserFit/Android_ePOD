package com.example.epod.application.auth.domain.repository;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.epod.application.auth.data.model.Auth;

import com.example.epod.application.auth.domain.repository.api.AuthAPI;
import com.example.epod.application.auth.domain.repository.api.AuthResponse;
import com.example.epod.utils.Request;
import com.example.epod.utils.helpers.ViewHelper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AuthRepository implements AuthRepositoryInterface {
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
    private final AuthAPI authAPI;
    private final Context context;

    private final MutableLiveData<Auth> authUserLiveData = new MutableLiveData<>();
    private final MutableLiveData<ViewHelper.LoadingState> loadingState = new MutableLiveData<>();

    public AuthRepository(Context context) {
        this.context = context;
        this.authAPI = Request.getRetrofitInstance(context).create(AuthAPI.class);
    }

    public LiveData<Auth> getAuthenticatedUser() {
        return authUserLiveData;
    }

    public LiveData<ViewHelper.LoadingState> getLoadingState() {
        return loadingState;
    }


    @Override
    public void login(String username, String password) {
        loadingState.setValue(ViewHelper.LoadingState.loading());

        Call<AuthResponse> call = authAPI.login(username, password);
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        @SuppressLint("CommitPrefEdits") SharedPreferences.Editor editor = sharedPreferences.edit();

        call.enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                try {
                    AuthResponse authResponse = response.body();
                    assert authResponse != null;
                    Auth authenticatedUser = authResponse.getAuthenticatedUser();
                    String prefixAuthorization = "Basic " + authenticatedUser.getAuthorization();

                    editor.putString(KEY_TOKEN, authenticatedUser.getAccess_token());
                    editor.putString(KEY_USER_ID, authenticatedUser.getId());
                    editor.putString(KEY_AUTHORIZATION, prefixAuthorization);
                    editor.putString(KEY_ACCOUNT, authenticatedUser.getCurrentAccount() != null ? authenticatedUser.getCurrentAccount().getUUID() : null);
                    editor.putString(KEY_LOCATION, authenticatedUser.getCurrentLocation() != null ? authenticatedUser.getCurrentLocation().getUUID() : null);
                    editor.putString(KEY_COMPANY, authenticatedUser.getCurrentCompany() != null ? authenticatedUser.getCurrentCompany().getUUID() : null);
                    editor.putString(KEY_USER_GROUP, authenticatedUser.getCurrentUserGroup() != null ? authenticatedUser.getCurrentUserGroup().getUUID() : null);

                    String userRole;
                    String userGroup = authenticatedUser.getCurrentUserGroup() != null ? authenticatedUser.getCurrentUserGroup().getUserGroup() : "";

                    if(userGroup.toLowerCase().contains("supervisor")) {
                        userRole = "SUPERVISOR";
                    } else if(userGroup.toLowerCase().contains("admin")) {
                        userRole = "ADMIN";
                    } else {
                        userRole = "ASSIGNEE";
                    }

                    editor.putString(KEY_USER_ROLE, userRole);
                    editor.apply();

                    authUserLiveData.postValue(authenticatedUser);
                    loadingState.setValue(ViewHelper.LoadingState.success());
                } catch (Exception error) {
                    Log.e("Error", error.getMessage());
                    loadingState.setValue(ViewHelper.LoadingState.error(error.getMessage()));
                }
            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable throwable) {
                Log.e("Repo", throwable.getMessage());
                loadingState.setValue(ViewHelper.LoadingState.error("Request failed"));
            }
        });
    }

    @Override
    public void logout() {

    }
}
