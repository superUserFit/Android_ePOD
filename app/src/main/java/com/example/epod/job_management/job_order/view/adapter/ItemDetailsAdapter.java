package com.example.epod.job_management.job_order.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.epod.R;
import com.example.epod.job_management.job_order.view.interfaces.JobOrderAdapterInterface;
import com.example.epod.job_management.job_order.view.holder.ItemDetailsViewHolder;
import com.example.epod.job_management.job_order.data.model.JobOrder;
import com.example.epod.job_management.job_order.data.model.JobOrderHasDetails;

import java.util.List;

public class ItemDetailsAdapter extends RecyclerView.Adapter<ItemDetailsViewHolder> implements JobOrderAdapterInterface {
    private List<JobOrderHasDetails> jobOrderHasDetails;
    private Context context;

    public ItemDetailsAdapter(List<JobOrderHasDetails> jobOrderHasDetails, Context context) {
        this.jobOrderHasDetails = jobOrderHasDetails;
        this.context = context;
    }

    @Override
    public void setJobOrderHasDetails(List<JobOrderHasDetails> jobOrderHasDetails) {
        this.jobOrderHasDetails = jobOrderHasDetails;
    }

    @NonNull
    @Override
    public ItemDetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.job_order_view_holder_item_details, parent, false);
        return new ItemDetailsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemDetailsViewHolder holder, int position) {
        JobOrderHasDetails itemDetails = jobOrderHasDetails.get(position);
        holder.bind(itemDetails);

        if(itemDetails.getItemType().equals("Stock")) {
            holder.itemDetailsCard.setCardBackgroundColor(context.getResources().getColor(R.color.orangeAccent));
        } else {
            holder.itemDetailsCard.setCardBackgroundColor(context.getResources().getColor(R.color.limeGreenAccent));
        }
    }

    @Override
    public int getItemCount() {
        return jobOrderHasDetails.size();
    }

    @Override
    public void setJobOrders(List<JobOrder> jobOrders) {
        throw new UnsupportedOperationException("Not supported in ItemDetailsAdapter");
    }
}
