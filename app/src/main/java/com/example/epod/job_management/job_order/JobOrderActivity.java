package com.example.epod.job_management.job_order;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ViewSwitcher;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.epod.auth.service.AuthService;
import com.example.epod.job_management.job_order.service.JobOrderService;
import com.example.epod.job_management.job_order.data.model.JobOrderHasDetails;
import com.facebook.shimmer.ShimmerFrameLayout;

import com.example.epod.R;
import com.example.epod.job_management.job_order.view.adapter.JobOrderAdapter;
import com.example.epod.job_management.job_order.view.adapter.TabButtonAdapter;
import com.example.epod.job_management.job_order.view.holder.TabButtonViewHolder;
import com.example.epod.job_management.job_order.data.model.JobOrder;
import com.example.epod.utils.Helper;
import com.example.epod.job_management.job_order.data.repository.JobOrderCallback;

import java.util.ArrayList;
import java.util.List;

public class JobOrderActivity extends AppCompatActivity implements JobOrderCallback {
    private JobOrderAdapter jobOrderAdapter;
    private TabButtonAdapter tabButtonAdapter;
    private JobOrderService jobOrderService;
    private AuthService authService;

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


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.job_order_activity_job_order);

        // Set AppBar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        getSupportActionBar().setTitle("Job Order");

        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                Helper.goBackActivity(JobOrderActivity.this);
            }
        };
        getOnBackPressedDispatcher().addCallback(this, callback);

        viewSwitcher = findViewById(R.id.viewSwitcher);
        loadingLayout = findViewById(R.id.job_order_loading);
        viewSwitcher.setDisplayedChild(0);

        List<TabButtonViewHolder.TabButton> tabButtonList = new ArrayList<>();

        for(String status: JOB_ORDER_STATUS) {
            tabButtonList.add(new TabButtonViewHolder.TabButton(status));
        }

        tabButtonAdapter = new TabButtonAdapter(tabButtonList, this);
        RecyclerView recyclerViewTabButton = findViewById(R.id.recyclerView_jobOrder_tabButton);
        LinearLayoutManager tabButtonLayout = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewTabButton.setLayoutManager(tabButtonLayout);
        recyclerViewTabButton.setAdapter(tabButtonAdapter);

        sortJobOrder = findViewById(R.id.sort_job_order);

        authService = new AuthService(this);
        jobOrderAdapter = new JobOrderAdapter(new ArrayList<>(), this);
        jobOrderService = new JobOrderService(this, jobOrderAdapter, authService);
        jobOrderService.getJobOrderByUser();

        // Set RecyclerView for job orders
        RecyclerView recyclerView_jobOrder = findViewById(R.id.recyclerView_jobOrder);
        recyclerView_jobOrder.setAdapter(jobOrderAdapter);
        recyclerView_jobOrder.setLayoutManager(new LinearLayoutManager(this));

        //  For search feature
        searchTextField = findViewById(R.id.searchBar);

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
                jobOrderService.toggleSortJobOrder();
            }
        });

        tabButtonAdapter.onClickListener(view -> {
            View parentView = (View) view.getParent();
            if (parentView instanceof RecyclerView) {
                int position = recyclerViewTabButton.getChildAdapterPosition(view);
                tabButtonAdapter.selectedTabIndex = position;
                tabButtonAdapter.notifyDataSetChanged();

                // Update job order service with selected index
                jobOrderService.setSelectedStatusIndex(position);
            } else {
                Log.e("TabButtonAdapter", "View is not a direct child of RecyclerView");
            }
        });

    }

    @Override
    public void onLoadJobOrders(List<JobOrder> jobOrders) {
        runOnUiThread(() -> {
            loadingLayout.stopShimmer();
            viewSwitcher.setDisplayedChild(1);
            this.jobOrders = jobOrders;
            jobOrderAdapter.setJobOrders(this.jobOrders);
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            Helper.goBackActivity(this);
            return true;
        }
        return super.onOptionsItemSelected(item);
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
