package com.example.epod.screens.job_management.job_order.main.adapter;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.epod.R;
import com.example.epod.screens.job_management.job_order.details.JobOrderDetailsActivity;
import com.example.epod.screens.job_management.job_order.interfaces.JobOrderAdapterInterface;
import com.example.epod.screens.job_management.job_order.main.holder.JobOrderViewHolder;
import com.example.epod.application.job_order.data.model.JobOrderModel;
import com.example.epod.application.job_order.data.model.JobOrderHasDetailsModel;

import java.util.List;

public class JobOrderAdapter extends RecyclerView.Adapter<JobOrderViewHolder> implements JobOrderAdapterInterface {
    private List<JobOrderModel> jobOrderModelList;
    private Context context;

    public JobOrderAdapter(List<JobOrderModel> jobOrderModelList, Context context) {
        this.jobOrderModelList = jobOrderModelList;
        this.context = context;
    }

    @Override
    public void setJobOrders(List<JobOrderModel> jobOrderModels) {
        this.jobOrderModelList = jobOrderModels;
        notifyDataSetChanged();
    }

    public void createJobOrder(JobOrderModel jobOrderModel) {
        this.jobOrderModelList.add(jobOrderModel);
        notifyItemInserted(this.jobOrderModelList.size() - 1);
    }

    public void removeJobOrder(int position) {
        if (position >= 0 && position < this.jobOrderModelList.size()) {
            this.jobOrderModelList.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void updateJobOrder(int position, JobOrderModel jobOrderModel) {
        if (position >= 0 && position < this.jobOrderModelList.size()) {
            this.jobOrderModelList.set(position, jobOrderModel);
            notifyItemChanged(position);
        }
    }

    @NonNull
    @Override
    public JobOrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.job_order_view_holder_card, parent, false);
        return new JobOrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JobOrderViewHolder holder, int position) {
        JobOrderModel jobOrderModel = jobOrderModelList.get(position);
        holder.bind(jobOrderModel);

        if (jobOrderModel.getUUID() != null) {
            holder.itemView.setOnClickListener(view -> {
                Intent intent = new Intent(context, JobOrderDetailsActivity.class);
                intent.putExtra("jobOrderId", jobOrderModel.getUUID());
                context.startActivity(intent);
            });
        } else {
            Toast.makeText(context, "UUID is null for this job order", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public int getItemCount() {
        return jobOrderModelList.size();
    }

    @Override
    public void setJobOrderHasDetails(List<JobOrderHasDetailsModel> jobOrderHasDetailModels) {
        throw new UnsupportedOperationException("Not supported in JobOrderAdapter");
    }
}
