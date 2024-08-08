package com.example.epod.job_management.view.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.epod.R;
import com.example.epod.job_management.JobManagementFragment;
import com.example.epod.job_management.job.view.JobFragment;
import com.example.epod.job_management.job_order.view.main.JobOrderFragment;
import com.example.epod.job_management.view.model.JobManagement;
import com.example.epod.job_management.view.holder.JobManagementViewHolder;

import java.util.List;

public class JobManagementAdapter extends RecyclerView.Adapter<JobManagementViewHolder> {
    Context context;
    List<JobManagement> itemList;

    public JobManagementAdapter(Context context, List<JobManagement> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public JobManagementViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new JobManagementViewHolder(LayoutInflater.from(context).inflate(R.layout.job_management_view_holder_card, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull JobManagementViewHolder holder, int position) {
        holder.icon.setImageResource(itemList.get(position).getIcon());
        holder.cardTitle.setText(itemList.get(position).getCardTitle());

        JobManagement jobManagement = itemList.get(position);

        holder.itemView.setOnClickListener(view -> {
            Intent intent;

            switch (jobManagement.getCardTitle()) {
                case "Job Order":
                    intent = new Intent(context, JobOrderFragment.class);
                    break;

                case "Job":
                    intent = new Intent(context, JobFragment.class);
                    break;

                default:
                    intent = new Intent(context, JobManagementFragment.class);
                    break;
            }
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            ((Activity) context).overridePendingTransition(R.anim.slide_in_from_center, R.anim.slide_out_to_left);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
}
