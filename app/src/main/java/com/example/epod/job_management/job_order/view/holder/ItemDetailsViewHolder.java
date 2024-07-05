package com.example.epod.job_management.job_order.view.holder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.epod.R;

public class ItemDetailsViewHolder extends RecyclerView.ViewHolder {
    public TextView itemType, itemCode, description, quantity, itemUOM;

    public ItemDetailsViewHolder(@NonNull View itemView) {
        super(itemView);
        itemType = itemView.findViewById(R.id.itemType);
        itemCode = itemView.findViewById(R.id.itemCode);
        description = itemView.findViewById(R.id.description);
        quantity = itemView.findViewById(R.id.quantity);
        itemUOM = itemView.findViewById(R.id.itemUOM);
    }
}
