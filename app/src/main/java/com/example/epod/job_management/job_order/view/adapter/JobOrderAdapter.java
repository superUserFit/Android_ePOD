package com.example.epod.job_management.job_order.view.adapter;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.epod.R;
import com.example.epod.job_management.job_order.JobOrderDetailsActivity;
import com.example.epod.job_management.job_order.view.interfaces.JobOrderAdapterInterface;
import com.example.epod.job_management.job_order.data.model.JobOrder;
import com.example.epod.job_management.job_order.view.holder.JobOrderViewHolder;
import com.example.epod.job_management.job_order.data.model.JobOrderHasDetails;

import java.util.List;

public class JobOrderAdapter extends RecyclerView.Adapter<JobOrderViewHolder> implements JobOrderAdapterInterface {
    private List<JobOrder> jobOrderList;
    private Context context;

    public JobOrderAdapter(List<JobOrder> jobOrderList, Context context) {
        this.jobOrderList = jobOrderList;
        this.context = context;
    }

    @Override
    public void setJobOrders(List<JobOrder> jobOrders) {
        this.jobOrderList = jobOrders;
        notifyDataSetChanged();
    }

    public void createJobOrder(JobOrder jobOrder) {
        this.jobOrderList.add(jobOrder);
        notifyItemInserted(this.jobOrderList.size() - 1);
    }

    public void removeJobOrder(int position) {
        if (position >= 0 && position < this.jobOrderList.size()) {
            this.jobOrderList.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void updateJobOrder(int position, JobOrder jobOrder) {
        if (position >= 0 && position < this.jobOrderList.size()) {
            this.jobOrderList.set(position, jobOrder);
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
        JobOrder jobOrder = jobOrderList.get(position);
        holder.bind(jobOrder);

        if (jobOrder.getUUID() != null) {
            holder.itemView.setOnClickListener(view -> {
                Intent intent = new Intent(context, JobOrderDetailsActivity.class);
                intent.putExtra("jobOrderId", jobOrder.getJobOrder());
                context.startActivity(intent);
            });
        } else {
            Toast.makeText(context, "UUID is null for this job order", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public int getItemCount() {
        return jobOrderList.size();
    }

    @Override
    public void setJobOrderHasDetails(List<JobOrderHasDetails> jobOrderHasDetails) {
        throw new UnsupportedOperationException("Not supported in JobOrderAdapter");
    }
}
