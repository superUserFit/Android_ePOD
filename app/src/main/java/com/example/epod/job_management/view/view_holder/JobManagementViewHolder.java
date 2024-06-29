package com.example.epod.job_management.view.view_holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.epod.R;

public class JobManagementViewHolder extends RecyclerView.ViewHolder {
    public ImageView icon;
    public TextView cardTitle;

    public JobManagementViewHolder(@NonNull View itemView) {
        super(itemView);
        icon = itemView.findViewById(R.id.icon);
        cardTitle = itemView.findViewById(R.id.cardTitle);
    }
}
