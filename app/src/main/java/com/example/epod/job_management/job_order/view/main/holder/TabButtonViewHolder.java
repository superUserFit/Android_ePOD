package com.example.epod.job_management.job_order.view.main.holder;

import android.view.View;
import android.widget.Button;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.epod.R;


public class TabButtonViewHolder extends RecyclerView.ViewHolder {
    public Button tabButton;

    public TabButtonViewHolder(@NonNull View itemView) {
        super(itemView);
        tabButton = itemView.findViewById(R.id.tabName);
    }

    public static class TabButton {
        private String name;

        public TabButton(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
