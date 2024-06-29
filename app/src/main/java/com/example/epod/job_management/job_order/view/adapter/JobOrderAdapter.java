package com.example.epod.job_management.job_order.view.adapter;

package com.example.epod.job_management.job_order.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.epod.R;
import com.example.epod.job_management.job_order.view.model.JobOrder;

import java.util.List;

public class JobOrderAdapter extends RecyclerView.Adapter<JobOrderAdapter.JobOrderViewHolder> {

    private List<JobOrder> jobOrderList;

    public JobOrderAdapter(List<JobOrder> jobOrderList) {
        this.jobOrderList = jobOrderList;
    }

    @NonNull
    @Override
    public JobOrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_holder_job_management_card, parent, false);
        return new JobOrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JobOrderViewHolder holder, int position) {
        JobOrder jobOrder = jobOrderList.get(position);
        holder.titleTextView.setText(jobOrder.getTitle());
        holder.descriptionTextView.setText(jobOrder.getDescription());
        holder.imageView.setImageResource(jobOrder.getImageResId());
    }

    @Override
    public int getItemCount() {
        return jobOrderList.size();
    }

    static class JobOrderViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView imageView;
        TextView titleTextView;
        TextView descriptionTextView;

        public JobOrderViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.card_view);
            imageView = itemView.findViewById(R.id.card_image);
            titleTextView = itemView.findViewById(R.id.card_title);
            descriptionTextView = itemView.findViewById(R.id.card_description);
        }
    }
}

