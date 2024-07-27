package com.example.epod.job_management.job_order.view.holder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.epod.R;
import com.example.epod.job_management.job_order.data.model.JobOrderHasDetails;

public class ItemDetailsViewHolder extends RecyclerView.ViewHolder {
    public CardView itemDetailsCard;
    public TextView itemType, itemCode, description, quantity, itemUOM;

    public ItemDetailsViewHolder(@NonNull View itemView) {
        super(itemView);
        itemDetailsCard = itemView.findViewById(R.id.itemDetailsCard);
        itemType = itemView.findViewById(R.id.itemType);
        itemCode = itemView.findViewById(R.id.itemCode);
        description = itemView.findViewById(R.id.description);
        quantity = itemView.findViewById(R.id.quantity);
        itemUOM = itemView.findViewById(R.id.itemUOM);
    }

    public void bind(JobOrderHasDetails jobOrderHasDetails) {
        itemCode.setText(jobOrderHasDetails.getItemCode() != null ? jobOrderHasDetails.getItemCode() : "");
        itemType.setText(jobOrderHasDetails.getItemType() != null ? jobOrderHasDetails.getItemType() : "");
        description.setText(jobOrderHasDetails.getDescription() != null ? jobOrderHasDetails.getDescription() : "");
        quantity.setText(jobOrderHasDetails.getDescription() != null ? jobOrderHasDetails.getQuantity() : "");
        itemUOM.setText(jobOrderHasDetails.getItemUOM() != null ? jobOrderHasDetails.getItemUOM() : "");
    }
}
