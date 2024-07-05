package com.example.epod.job_management.job_order.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.epod.R;
import com.example.epod.job_management.job_order.view.holder.ItemDetailsViewHolder;
import com.example.epod.job_management.job_order.view.model.JobOrderHasDetails;

import java.util.List;

public class ItemDetailsAdapter extends RecyclerView.Adapter<ItemDetailsViewHolder> {
    private List<JobOrderHasDetails> jobOrderHasDetails;
    private Context context;

    public ItemDetailsAdapter(List<JobOrderHasDetails> jobOrderHasDetails, Context context) {
        this.jobOrderHasDetails = jobOrderHasDetails;
        this.context = context;
    }

    @NonNull
    @Override
    public ItemDetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.job_order_layout_item_details, parent, false);
        return new ItemDetailsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemDetailsViewHolder holder, int position) {
        JobOrderHasDetails itemDetails = jobOrderHasDetails.get(position);
        holder.itemCode.setText(itemDetails.getItemCode());
        holder.itemType.setText(itemDetails.getItemType());
        holder.description.setText(itemDetails.getDescription());
        holder.quantity.setText(itemDetails.getDescription());
        holder.itemUOM.setText(itemDetails.getItemUOM());
    }

    @Override
    public int getItemCount() {
        return jobOrderHasDetails.size();
    }
}
