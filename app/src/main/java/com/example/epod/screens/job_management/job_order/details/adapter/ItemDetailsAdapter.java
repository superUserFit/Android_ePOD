package com.example.epod.screens.job_management.job_order.details.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.epod.R;
import com.example.epod.screens.job_management.job_order.details.holder.ItemDetailsViewHolder;
import com.example.epod.screens.job_management.job_order.interfaces.JobOrderAdapterInterface;
import com.example.epod.application.job_order.data.model.JobOrderModel;
import com.example.epod.application.job_order.data.model.JobOrderHasDetailsModel;

import java.util.List;

public class ItemDetailsAdapter extends RecyclerView.Adapter<ItemDetailsViewHolder> implements JobOrderAdapterInterface {
    private List<JobOrderHasDetailsModel> jobOrderHasDetailModels;
    private Context context;

    public ItemDetailsAdapter(List<JobOrderHasDetailsModel> jobOrderHasDetailModels, Context context) {
        this.jobOrderHasDetailModels = jobOrderHasDetailModels;
        this.context = context;
    }

    @Override
    public void setJobOrderHasDetails(List<JobOrderHasDetailsModel> jobOrderHasDetailModels) {
        this.jobOrderHasDetailModels = jobOrderHasDetailModels;
    }

    @NonNull
    @Override
    public ItemDetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.job_order_view_holder_item_details, parent, false);
        return new ItemDetailsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemDetailsViewHolder holder, int position) {
        JobOrderHasDetailsModel itemDetails = jobOrderHasDetailModels.get(position);
        holder.bind(itemDetails);

        if(itemDetails.getItemType().equals("Stock")) {
            holder.itemDetailsCard.setCardBackgroundColor(context.getResources().getColor(R.color.orangeAccent));
        } else {
            holder.itemDetailsCard.setCardBackgroundColor(context.getResources().getColor(R.color.limeGreenAccent));
        }
    }

    @Override
    public int getItemCount() {
        return jobOrderHasDetailModels.size();
    }

    @Override
    public void setJobOrders(List<JobOrderModel> jobOrderModels) {
        throw new UnsupportedOperationException("Not supported in ItemDetailsAdapter");
    }
}
