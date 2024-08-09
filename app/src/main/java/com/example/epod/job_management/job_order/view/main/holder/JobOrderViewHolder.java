package com.example.epod.job_management.job_order.view.main.holder;

import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.epod.R;
import com.example.epod.job_management.job_order.data.model.JobOrderModel;

public class JobOrderViewHolder extends RecyclerView.ViewHolder {
    public TextView customerName, docNo, docDate, jobOrderStatus;
    public TextView pickupLocation, pickupAddress;
    public TextView deliveryLocation, deliveryAddress;
    public TextView startPickupAt, endPickupAt;

    public JobOrderViewHolder(@NonNull View itemView) {
        super(itemView);
        customerName = itemView.findViewById(R.id.customerName);
        docNo = itemView.findViewById(R.id.docNo);
        docDate = itemView.findViewById(R.id.docDate);
        jobOrderStatus = itemView.findViewById(R.id.jobOrderStatus);

        pickupLocation = itemView.findViewById(R.id.pickupLocation);
        pickupAddress = itemView.findViewById(R.id.pickupAddress);
        deliveryLocation = itemView.findViewById(R.id.deliveryLocation);
        deliveryAddress = itemView.findViewById(R.id.deliveryAddress);

        startPickupAt = itemView.findViewById(R.id.startPickupAt);
        endPickupAt = itemView.findViewById(R.id.endPickupAt);
    }

    public void bind(JobOrderModel jobOrderModel) {
        customerName.setText(jobOrderModel.getCustomerName() != null ? jobOrderModel.getCustomerName() : "Unknown");
        docNo.setText(jobOrderModel.getDocNo() != null ? jobOrderModel.getDocNo() : "N/A");
        docDate.setText(jobOrderModel.getDocDate() != null ? jobOrderModel.getDocDate() : "N/A");
        jobOrderStatus.setText(jobOrderModel.getStatus() != null ? jobOrderModel.getStatus() : "Unknown");

        pickupLocation.setText(jobOrderModel.getPickupLocation() != null ? jobOrderModel.getPickupLocation() : "Unknown Location");
        pickupAddress.setText(jobOrderModel.getPickupAddress() != null ? jobOrderModel.getPickupAddress() : "Unknown Address");
        deliveryLocation.setText(jobOrderModel.getDeliveryLocation() != null ? jobOrderModel.getDeliveryLocation() : "Unknown Location");
        deliveryAddress.setText(jobOrderModel.getDeliveryAddress() != null ? jobOrderModel.getDeliveryAddress() : "Unknown Address");

        startPickupAt.setText(jobOrderModel.getStartPickupAt() != null ? jobOrderModel.getStartPickupAt() : "");
        endPickupAt.setText(jobOrderModel.getEndPickupAt() != null ? jobOrderModel.getEndPickupAt() : "");

        jobOrderStatus.setBackgroundResource(R.drawable.ui_rounded_32);

        int color;
        if (jobOrderModel.getStatus() != null) {
            switch (jobOrderModel.getStatus()) {
                case "Unassigned":
                    color = ContextCompat.getColor(itemView.getContext(), R.color.darkGrey);
                    jobOrderStatus.setTextColor(ContextCompat.getColor(itemView.getContext(), R.color.white));
                    break;

                case "Assigned":
                    color = ContextCompat.getColor(itemView.getContext(), R.color.blue);
                    jobOrderStatus.setTextColor(ContextCompat.getColor(itemView.getContext(), R.color.white));
                    break;

                case "Started":
                    color = ContextCompat.getColor(itemView.getContext(), R.color.yellow);
                    jobOrderStatus.setTextColor(ContextCompat.getColor(itemView.getContext(), R.color.black));
                    break;

                case "Progressing":
                    color = ContextCompat.getColor(itemView.getContext(), R.color.darkYellow);
                    jobOrderStatus.setTextColor(ContextCompat.getColor(itemView.getContext(), R.color.white));
                    break;

                case "In Transit":
                    color = ContextCompat.getColor(itemView.getContext(), R.color.darkGreen);
                    jobOrderStatus.setTextColor(ContextCompat.getColor(itemView.getContext(), R.color.white));
                    break;

                case "Delivered":
                    color = ContextCompat.getColor(itemView.getContext(), R.color.limeGreen);
                    jobOrderStatus.setTextColor(ContextCompat.getColor(itemView.getContext(), R.color.white));
                    break;

                case "Completed":
                    color = ContextCompat.getColor(itemView.getContext(), R.color.darkGreen);
                    jobOrderStatus.setTextColor(ContextCompat.getColor(itemView.getContext(), R.color.white));
                    break;

                default:
                    color = ContextCompat.getColor(itemView.getContext(), R.color.white);
                    jobOrderStatus.setTextColor(ContextCompat.getColor(itemView.getContext(), R.color.black));
                    break;
            }
        } else {
            color = ContextCompat.getColor(itemView.getContext(), R.color.redAccent);
            jobOrderStatus.setTextColor(ContextCompat.getColor(itemView.getContext(), R.color.white));
        }

        GradientDrawable drawable = (GradientDrawable) jobOrderStatus.getBackground();
        if (drawable != null) {
            drawable.setColor(color);
        }
    }
}
