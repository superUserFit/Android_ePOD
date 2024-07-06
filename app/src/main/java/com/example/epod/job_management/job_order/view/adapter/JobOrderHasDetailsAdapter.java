package com.example.epod.job_management.job_order.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.epod.R;
import com.example.epod.job_management.job_order.interfaces.JobOrderAdapterInterface;
import com.example.epod.job_management.job_order.view.holder.JobOrderHasDetailsViewHolder;
import com.example.epod.job_management.job_order.view.model.JobOrder;
import com.example.epod.job_management.job_order.view.model.JobOrderHasDetails;

import java.util.List;

public class JobOrderHasDetailsAdapter extends RecyclerView.Adapter<JobOrderHasDetailsViewHolder> implements JobOrderAdapterInterface {
    private List<JobOrderHasDetails> jobOrderHasDetails;
    private Context context;

    public  JobOrderHasDetailsAdapter(List<JobOrderHasDetails> jobOrderHasDetails, Context context) {
        this.jobOrderHasDetails = jobOrderHasDetails;
        this.context = context;
    }

    @Override
    public void setJobOrderHasDetails(List<JobOrderHasDetails> jobOrderHasDetails) {
        this.jobOrderHasDetails = jobOrderHasDetails;
    }

    @NonNull
    @Override
    public JobOrderHasDetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.job_order_layout_item_details, parent, false);
        return new JobOrderHasDetailsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JobOrderHasDetailsViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return jobOrderHasDetails.size();
    }

    @Override
    public void setJobOrders(List<JobOrder> jobOrders) {

    }

    @Override
    public void setJobOrder(JobOrder jobOrder) {

    }
}
