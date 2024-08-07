package com.example.epod.job_management.job_order.view.main;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ViewSwitcher;
import androidx.activity.OnBackPressedCallback;
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
import com.example.epod.utils.Helper;
import com.example.epod.job_management.job_order.data.repository.JobOrderCallback;

import java.util.ArrayList;
import java.util.List;

public class JobOrderActivity extends Fragment implements JobOrderCallback {
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
        View rootView = inflater.inflate(R.layout.job_order_activity_job_order, container, false);

        // Set AppBar
        Toolbar toolbar = rootView.findViewById(R.id.toolbar);
        AppCompatActivity activity = (AppCompatActivity) getActivity();

        if(activity != null) {
            activity.setSupportActionBar(toolbar);
            if(activity.getSupportActionBar() != null) {
                activity.getSupportActionBar().setDisplayShowHomeEnabled(true);
                activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                activity.getSupportActionBar().setTitle("Job Order");
            }
        }

        viewSwitcher = rootView.findViewById(R.id.viewSwitcher);
        loadingLayout = rootView.findViewById(R.id.job_order_loading);
        viewSwitcher.setDisplayedChild(0);

        List<TabButtonViewHolder.TabButton> tabButtonList = new ArrayList<>();

        for(String status: JOB_ORDER_STATUS) {
            tabButtonList.add(new TabButtonViewHolder.TabButton(status));
        }

        tabButtonAdapter = new TabButtonAdapter(tabButtonList, getContext());
        RecyclerView recyclerViewTabButton = rootView.findViewById(R.id.recyclerView_jobOrder_tabButton);
        LinearLayoutManager tabButtonLayout = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewTabButton.setLayoutManager(tabButtonLayout);
        recyclerViewTabButton.setAdapter(tabButtonAdapter);

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
                if(isLoading) {
                    loadingLayout.startShimmer();
                    viewSwitcher.setDisplayedChild(0);
                } else {
                    loadingLayout.stopShimmer();
                    viewSwitcher.setDisplayedChild(1);
                }
            }
        });

        jobOrderViewModel.loadJobOrders("desc");

        sortJobOrder = rootView.findViewById(R.id.sort_job_order);
        jobOrderAdapter = new JobOrderAdapter(new ArrayList<>(), getContext());

        // Set RecyclerView for job orders
        RecyclerView recyclerView_jobOrder = rootView.findViewById(R.id.recyclerView_jobOrder);
        recyclerView_jobOrder.setAdapter(jobOrderAdapter);
        recyclerView_jobOrder.setLayoutManager(new LinearLayoutManager(getContext()));

        //  For search feature
        searchTextField = rootView.findViewById(R.id.searchBar);

        searchTextField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence text, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence text, int start, int before, int count) {
                searchJobOrders(text.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        sortJobOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jobOrderViewModel.toggleSortJobOrders();
            }
        });

        tabButtonAdapter.onClickListener(view -> {
            View parentView = (View) view.getParent();
            if (parentView instanceof RecyclerView) {
                int position = recyclerViewTabButton.getChildAdapterPosition(view);
                tabButtonAdapter.selectedTabIndex = position;
                tabButtonAdapter.notifyDataSetChanged();
            } else {
                Log.e("TabButtonAdapter", "View is not a direct child of RecyclerView");
            }
        });

        return rootView;
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
        for(JobOrder jobOrder : jobOrders) {
            if(jobOrder.getCustomerName() != null) {
                if(jobOrder.getCustomerName().toLowerCase().contains(searchQuery.toLowerCase())) {
                    filteredJobOrders.add(jobOrder);
                }
            }
        }
        jobOrderAdapter.setJobOrders(filteredJobOrders);
    }

    @Override
    public void onLoadJobOrder(JobOrder jobOrder) {

    }

    @Override
    public void onLoadJobOrderDetails(List<JobOrderHasDetails> jobOrderHasDetails) {

    }
}
