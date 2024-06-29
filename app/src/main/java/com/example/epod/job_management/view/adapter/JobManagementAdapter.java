package com.example.epod.job_management.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.epod.R;
import com.example.epod.job_management.view.model.JobManagement;
import com.example.epod.job_management.view.view_holder.JobManagementViewHolder;

import java.util.List;

public class JobManagementAdapter extends RecyclerView.Adapter<JobManagementViewHolder> {
    Context context;
    List<JobManagement> itemList;

    public JobManagementAdapter(Context context, List itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public JobManagementViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new JobManagementViewHolder(LayoutInflater.from(context).inflate(R.layout.view_holder_job_management_card, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull JobManagementViewHolder holder, int position) {
        holder.icon.setImageResource(itemList.get(position).getIcon());
        holder.cardTitle.setText(itemList.get(position).getCardTitle());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
}
