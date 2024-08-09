package com.example.epod.job_management.job_order.view.main;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ViewSwitcher;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.epod.job_management.job_order.data.model.JobOrderHasDetails;
import com.example.epod.job_management.job_order.view.main.model.JobOrderViewModel;
import com.facebook.shimmer.ShimmerFrameLayout;

import com.example.epod.R;
import com.example.epod.job_management.job_order.view.main.adapter.JobOrderAdapter;
import com.example.epod.job_management.job_order.view.main.adapter.TabButtonAdapter;
import com.example.epod.job_management.job_order.view.main.holder.TabButtonViewHolder;
import com.example.epod.job_management.job_order.data.model.JobOrder;
import com.example.epod.job_management.job_order.data.repository.JobOrderCallback;

import java.util.ArrayList;
import java.util.List;

public class JobOrderFragment extends Fragment implements JobOrderCallback {
    private JobOrderAdapter jobOrderAdapter;
    private TabButtonAdapter tabButtonAdapter;
    private JobOrderViewModel jobOrderViewModel;

    private ViewSwitcher viewSwitcher;
    private ShimmerFrameLayout loadingLayout;
    private EditText searchTextField;
    private List<JobOrder> jobOrders = new ArrayList<>();
    private ImageButton sortJobOrder;
    private static final String[] JOB_ORDER_STATUS = {
            "All",
            "Unassigned",
            "Assigned",
            "Started",
            "Progressing",
            "In Transit",
            "Delivered",
            "Completed"
    };

    @SuppressLint({"WrongViewCast", "NotifyDataSetChanged"})
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.job_order_activity_job_order, container, false);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialize views
        viewSwitcher = view.findViewById(R.id.viewSwitcher);
        loadingLayout = view.findViewById(R.id.job_order_loading);
        searchTextField = view.findViewById(R.id.searchBar);
        sortJobOrder = view.findViewById(R.id.sort_job_order);

        // Set up the AppBar
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        AppCompatActivity activity = (AppCompatActivity) getActivity();

        if (activity != null) {
            activity.setSupportActionBar(toolbar);
            if (activity.getSupportActionBar() != null) {
                activity.getSupportActionBar().setDisplayShowHomeEnabled(false);
                activity.getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                activity.getSupportActionBar().setTitle("Job Order");
            }
        }

        // Initialize and set up the JobOrderAdapter
        jobOrderAdapter = new JobOrderAdapter(new ArrayList<>(), getContext());
        RecyclerView recyclerView_jobOrder = view.findViewById(R.id.recyclerView_jobOrder);
        Context context = getContext();
        if (context != null) {
            recyclerView_jobOrder.setAdapter(jobOrderAdapter);
            recyclerView_jobOrder.setLayoutManager(new LinearLayoutManager(context));
        }

        // Set up the TabButtonAdapter
        List<TabButtonViewHolder.TabButton> tabButtonList = new ArrayList<>();
        for (String status : JOB_ORDER_STATUS) {
            tabButtonList.add(new TabButtonViewHolder.TabButton(status));
        }

        RecyclerView recyclerViewTabButton = view.findViewById(R.id.recyclerView_jobOrder_tabButton);
        if (context != null) {
            tabButtonAdapter = new TabButtonAdapter(tabButtonList, context);
            LinearLayoutManager tabButtonLayout = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
            recyclerViewTabButton.setLayoutManager(tabButtonLayout);
            recyclerViewTabButton.setAdapter(tabButtonAdapter);

            tabButtonAdapter.onClickListener(tab -> {
                View parentView = (View) tab.getParent();
                if (parentView instanceof RecyclerView) {
                    int position = recyclerViewTabButton.getChildAdapterPosition(tab);
                    tabButtonAdapter.selectedTabIndex = position;
                    tabButtonAdapter.notifyDataSetChanged();
                }
            });
        }

        jobOrderViewModel = new ViewModelProvider(this).get(JobOrderViewModel.class);

        jobOrderViewModel.getJobOrders().observe(getViewLifecycleOwner(), new Observer<List<JobOrder>>() {
            @Override
            public void onChanged(List<JobOrder> jobOrders) {
                loadingLayout.stopShimmer();
                viewSwitcher.setDisplayedChild(1);
                jobOrderAdapter.setJobOrders(jobOrders);
            }
        });

        jobOrderViewModel.getIsLoading().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isLoading) {
                if (isLoading) {
                    loadingLayout.startShimmer();
                    viewSwitcher.setDisplayedChild(0);
                } else {
                    loadingLayout.stopShimmer();
                    viewSwitcher.setDisplayedChild(1);
                }
            }
        });

        jobOrderViewModel.loadJobOrders("desc");

        // Set up search feature
        searchTextField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence text, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence text, int start, int before, int count) {
                searchJobOrders(text.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });

        // Set up sort button
        sortJobOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jobOrderViewModel.toggleSortJobOrders();
            }
        });
    }


    @Override
    public void onLoadJobOrders(List<JobOrder> jobOrders) {
        requireActivity().runOnUiThread(() -> {
            loadingLayout.stopShimmer();
            viewSwitcher.setDisplayedChild(1);
            this.jobOrders = jobOrders;
            jobOrderAdapter.setJobOrders(this.jobOrders);
        });
    }

    private void searchJobOrders(String searchQuery) {
        List<JobOrder> filteredJobOrders = new ArrayList<>();
        for (JobOrder jobOrder : jobOrders) {
            if (jobOrder.getCustomerName() != null) {
                if (jobOrder.getCustomerName().toLowerCase().contains(searchQuery.toLowerCase())) {
                    filteredJobOrders.add(jobOrder);
                }
            }
        }
        jobOrderAdapter.setJobOrders(filteredJobOrders);
    }

    @Override
    public void onLoadJobOrder(JobOrder jobOrder) {}

    @Override
    public void onLoadJobOrderDetails(List<JobOrderHasDetails> jobOrderHasDetails) {}
}
