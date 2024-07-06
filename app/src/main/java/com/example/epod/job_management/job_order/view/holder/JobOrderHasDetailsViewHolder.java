package com.example.epod.job_management.job_order.view.holder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.epod.R;

public class JobOrderHasDetailsViewHolder extends RecyclerView.ViewHolder {
    public TextView docNo, docDate, deadline, tripType, description;
    public TextView customerName, attentionName, phoneNo, address;

    public JobOrderHasDetailsViewHolder(@NonNull View itemView) {
        super(itemView);
        docNo = itemView.findViewById(R.id.docNo);
        docDate = itemView.findViewById(R.id.docDate);
        deadline = itemView.findViewById(R.id.deadline);
        tripType = itemView.findViewById(R.id.tripType);
        description = itemView.findViewById(R.id.description);

        customerName = itemView.findViewById(R.id.customerName);
        attentionName = itemView.findViewById(R.id.attentionName);
        phoneNo = itemView.findViewById(R.id.phoneNo);
        address = itemView.findViewById(R.id.address);
    }
}
