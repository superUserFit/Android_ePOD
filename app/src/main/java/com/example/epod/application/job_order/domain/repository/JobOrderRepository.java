package com.example.epod.application.job_order.domain.repository;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.epod.application.job_order.data.model.JobOrderModel;
import com.example.epod.application.job_order.data.model.JobOrderHasDetailsModel;
import com.example.epod.application.job_order.domain.repository.api.JobOrderAPI;
import com.example.epod.application.job_order.domain.repository.api.JobOrderResponse;
import com.example.epod.utils.NetworkModule;

import com.example.epod.utils.helpers.RepositoryHelper;
import com.example.epod.utils.helpers.ViewHelper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.Collections;
import java.util.List;


public class JobOrderRepository implements JobOrderRepositoryInterface {
    private final JobOrderAPI jobOrderApi;
    private final RepositoryHelper repositoryHelper;

    private final MutableLiveData<List<JobOrderModel>> jobOrdersLiveData = new MutableLiveData<>();
    private final MutableLiveData<JobOrderModel> jobOrderLiveData = new MutableLiveData<>();

    private final MutableLiveData<ViewHelper.LoadingState> loadingState = new MutableLiveData<>();


    public JobOrderRepository(Context context) {
        this.jobOrderApi = NetworkModule.getRetrofitInstance(context).create(JobOrderAPI.class);
        this.repositoryHelper = new RepositoryHelper(context);
    }

    public LiveData<List<JobOrderModel>> getJobOrders() {
        return jobOrdersLiveData;
    }

    public LiveData<JobOrderModel> getJobOrder() {
        return jobOrderLiveData;
    }

    public LiveData<ViewHelper.LoadingState> getLoadingState() { return loadingState; }


    @Override
    public void getJobOrderByUser(String order) {
        loadingState.postValue(ViewHelper.LoadingState.loading());

        String sort = "createdAt";
        String authorization = repositoryHelper.getAuthorization();

        if (authorization == null || authorization.isEmpty()) {
            Log.e("Authorization Error", "Authorization token is null or empty");
            loadingState.postValue(ViewHelper.LoadingState.error("Authorization token is missing"));
            return;
        }

        Log.e("Authorization", authorization);
        Call<JobOrderResponse> call = jobOrderApi.getJobOrderByUser(sort, order, authorization);

        call.enqueue(new Callback<JobOrderResponse>() {
            @Override
            public void onResponse(Call<JobOrderResponse> call, Response<JobOrderResponse> response) {
                try {
                    JobOrderResponse jobOrderResponse = response.body();
                    List<JobOrderModel> jobOrders = jobOrderResponse.getJobOrders();

                    if(jobOrders == null || jobOrders.isEmpty()) {
                        jobOrders = Collections.emptyList();
                    }

                    jobOrdersLiveData.postValue(jobOrders);
                    loadingState.postValue(ViewHelper.LoadingState.success());
                } catch (Exception error) {
                    jobOrdersLiveData.postValue(Collections.emptyList());
                    loadingState.postValue(ViewHelper.LoadingState.error(error.getMessage()));
                }
            }

            @Override
            public void onFailure(Call<JobOrderResponse> call, Throwable throwable) {
                Log.e("Error: ", throwable.getMessage());
            }
        });
    }

    @Override
    public void getUpdateJobOrder(String jobOrderId) {
        String authorization = repositoryHelper.getAuthorization();
        Call<JobOrderResponse> call = jobOrderApi.getUpdateJobOrder(jobOrderId, authorization);

        call.enqueue(new Callback<JobOrderResponse>() {
            @Override
            public void onResponse(Call<JobOrderResponse> call, Response<JobOrderResponse> response) {
                try {
                    JobOrderResponse jobOrderResponse = response.body();
                    JobOrderModel jobOrder = jobOrderResponse.getJobOrder();

                    jobOrderLiveData.postValue(jobOrder);
                } catch (Exception error) {
                    Log.e("Error", error.getMessage());
                }
            }

            @Override
            public void onFailure(Call<JobOrderResponse> call, Throwable throwable) {
                Log.e("Error", throwable.getMessage());
            }
        });
    }

    @Override
    public void getUpdateJobOrderHasDetails(String jobOrderId) {
        String authorization = repositoryHelper.getAuthorization();
        Call<JobOrderResponse> call = jobOrderApi.getUpdateJobOrderHasDetails(jobOrderId, authorization);

        call.enqueue(new Callback<JobOrderResponse>() {
            @Override
            public void onResponse(Call<JobOrderResponse> call, Response<JobOrderResponse> response) {
                try {
                    JobOrderResponse jobOrderResponse = response.body();
                    if(jobOrderResponse != null) {
                        List<JobOrderHasDetailsModel> jobOrderHasDetailModels = jobOrderResponse.getJobOrderHasDetails();
                        if(jobOrderHasDetailModels == null || jobOrderHasDetailModels.isEmpty()) {
                            jobOrderHasDetailModels = Collections.emptyList();
                        }
                    }
                } catch (Exception error) {
                    Log.e("Error", error.getMessage());
                }
            }

            @Override
            public void onFailure(Call<JobOrderResponse> call, Throwable throwable) {
//                jobOrderCallback.setJobOrderDetails(Collections.emptyList());
            }
        });
    }

    @Override
    public void getUpdateJobOrderHasSupervisors() {

    }

    @Override
    public void getUpdateJobOrderHasAssignmentData() {

    }

    @Override
    public void createJobOrderHasAssignment() {

    }

    @Override
    public void updateJobOrderHasAssignment() {

    }

    @Override
    public void updateJobOrderHasAssignee() {

    }

    @Override
    public void getJobOrderHasAssignmentByUser() {

    }

    @Override
    public void getUpdateJobOrderHasAssignment() {

    }

    @Override
    public void getUpdateJobOrderHasAssignee() {

    }

    @Override
    public void acknowledgedJobOrderHasAssignment() {

    }

    @Override
    public void startJobOrderHasAssignment() {

    }

    @Override
    public void pickupJobOrderHasAssignment() {

    }

    @Override
    public void deliverJobOrderHasAssignment() {

    }

    @Override
    public void acceptJobOrderHasAssignment() {

    }
}
