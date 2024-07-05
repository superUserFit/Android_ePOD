package com.example.epod.job_management.job_order.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.epod.R;
import com.example.epod.job_management.job_order.interfaces.JobOrderAdapterInterface;
import com.example.epod.job_management.job_order.view.holder.JobOrderDetailsViewHolder;
import com.example.epod.job_management.job_order.view.model.JobOrder;
import com.example.epod.job_management.job_order.view.model.JobOrderHasDetails;

import java.util.List;

public class JobOrderDetailsAdapter extends RecyclerView.Adapter<JobOrderDetailsViewHolder> implements JobOrderAdapterInterface {
    private JobOrder jobOrder;
    private Context context;

    public JobOrderDetailsAdapter(Context context) {
        this.context = context;
    }

    public void setJobOrder(JobOrder jobOrder) {
        this.jobOrder = jobOrder;
        notifyDataSetChanged();
    }

    @Override
    public void setJobOrderHasDetails(List<JobOrderHasDetails> jobOrderHasDetails) {

    }

    @NonNull
    @Override
    public JobOrderDetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.job_order_activity_job_order_details, parent, false);
        return new JobOrderDetailsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JobOrderDetailsViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    @Override
    public void setJobOrders(List<JobOrder> jobOrders) {
        throw new UnsupportedOperationException("Not supported in JobOrderDetailsAdapter");
    }
}
