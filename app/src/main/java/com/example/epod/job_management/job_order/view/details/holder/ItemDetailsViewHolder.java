package com.example.epod.job_management.job_order.view.details.holder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.epod.R;
import com.example.epod.job_management.job_order.data.model.JobOrderHasDetailsModel;

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

    public void bind(JobOrderHasDetailsModel jobOrderHasDetailsModel) {
        itemCode.setText(jobOrderHasDetailsModel.getItemCode() != null ? jobOrderHasDetailsModel.getItemCode() : "");
        itemType.setText(jobOrderHasDetailsModel.getItemType() != null ? jobOrderHasDetailsModel.getItemType() : "");
        description.setText(jobOrderHasDetailsModel.getDescription() != null ? jobOrderHasDetailsModel.getDescription() : "");
        quantity.setText(jobOrderHasDetailsModel.getDescription() != null ? jobOrderHasDetailsModel.getQuantity() : "");
        itemUOM.setText(jobOrderHasDetailsModel.getItemUOM() != null ? jobOrderHasDetailsModel.getItemUOM() : "");
    }
}
