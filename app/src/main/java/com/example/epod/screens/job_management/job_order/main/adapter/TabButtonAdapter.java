package com.example.epod.screens.job_management.job_order.main.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.epod.R;
import com.example.epod.screens.job_management.job_order.main.holder.TabButtonViewHolder;

import java.util.List;

public class TabButtonAdapter extends RecyclerView.Adapter<TabButtonViewHolder> {
    private final List<TabButtonViewHolder.TabButton> tabButtonList;
    private View.OnClickListener onClickListener;
    private final Context context;
    public int selectedTabIndex = 0;

    public TabButtonAdapter(List<TabButtonViewHolder.TabButton> tabButtonList, Context context) {
        this.tabButtonList = tabButtonList;
        this.context = context;
    }

    public void onClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public TabButtonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.job_order_view_holder_tab, parent, false);
        return new TabButtonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TabButtonViewHolder holder, @SuppressLint("RecyclerView") int position) {
        TabButtonViewHolder.TabButton tabButton = tabButtonList.get(position);
        holder.tabButton.setText(tabButton.getName());
        holder.tabButton.setBackgroundResource(R.drawable.ui_rounded_32);

        int color;
        if (selectedTabIndex == position) {
            color = ContextCompat.getColor(context, R.color.deepOrange);
            holder.tabButton.setTextColor(context.getResources().getColor(R.color.white));
        } else {
            color = ContextCompat.getColor(context, R.color.grey);
            holder.tabButton.setTextColor(context.getResources().getColor(R.color.black));
        }

        GradientDrawable drawable = (GradientDrawable) holder.tabButton.getBackground();
        if (drawable != null) {
            drawable.setColor(color);
        }


        holder.tabButton.setOnClickListener(view -> {
            if(onClickListener != null) {
                onClickListener.onClick(view);
            }

            selectedTabIndex = position;
            notifyDataSetChanged();
        });
    }

    @Override
    public int getItemCount() {
        return tabButtonList.size();
    }
}
