package com.example.epod.job_management.job_order.view.details;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ViewSwitcher;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.epod.R;
import com.example.epod.databinding.JobOrderActivityJobOrderDetailsBinding;
import com.example.epod.job_management.job_order.data.repository.JobOrderCallback;
import com.example.epod.job_management.job_order.view.details.adapter.ItemDetailsAdapter;
import com.example.epod.job_management.job_order.data.model.JobOrderModel;
import com.example.epod.job_management.job_order.data.model.JobOrderHasDetailsModel;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.ArrayList;
import java.util.List;

public class JobOrderDetailsActivity extends AppCompatActivity implements JobOrderCallback {
    private ItemDetailsAdapter itemDetailsAdapter;
    private ViewSwitcher viewSwitcher;
    private ShimmerFrameLayout loadingLayout;
    private LinearLayout itemDetailsCard;
    private JobOrderActivityJobOrderDetailsBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = JobOrderActivityJobOrderDetailsBinding.inflate(getLayoutInflater());
        setContentView(R.layout.job_order_activity_job_order_details);
        setContentView(binding.getRoot());

        // Set AppBar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle("Job Order Details");

            toolbar.setTitleTextColor(getResources().getColor(R.color.deepOrange));
            if (toolbar.getNavigationIcon() != null) {
                toolbar.getNavigationIcon().setTint(getResources().getColor(R.color.deepOrange));
            }
        }

        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                finish();
            }
        };

        getOnBackPressedDispatcher().addCallback(this, callback);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) View mainView = findViewById(R.id.main);
        if (mainView != null) {
            ViewCompat.setOnApplyWindowInsetsListener(mainView, (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;
            });
        } else {
            Log.e("JobOrderDetailsActivity", "Main view (R.id.main) not found in layout");
        }

        String jobOrderId = getIntent().getExtras().getString("jobOrderId");
        itemDetailsAdapter = new ItemDetailsAdapter(new ArrayList<>(), this);

        //  Set RecyclerView for job order details
        itemDetailsCard = findViewById(R.id.itemDetails);

        RecyclerView recyclerView_jobOrderDetails_itemDetails = findViewById(R.id.recyclerView_jobOrderDetails_itemDetails);
        recyclerView_jobOrderDetails_itemDetails.setAdapter(itemDetailsAdapter);
        recyclerView_jobOrderDetails_itemDetails.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onLoadJobOrder(JobOrderModel jobOrderModel) {
        runOnUiThread(() -> {
            binding.docNo.setText(jobOrderModel.getDocNo() != null ? jobOrderModel.getDocNo() : "");
            binding.docDate.setText(jobOrderModel.getDocDate() != null ? jobOrderModel.getDocDate() : "");
            binding.deadline.setText(jobOrderModel.getDeadline() != null ? jobOrderModel.getDeadline() : "");
            binding.tripType.setText(jobOrderModel.getTripType() != null ? jobOrderModel.getTripType() : "");
            binding.description.setText(jobOrderModel.getDescription() != null ? jobOrderModel.getDescription() : "");
        });
    }

    public void expandItemDetails(View view) {
        if (itemDetailsCard.getVisibility() == View.GONE) {
            itemDetailsCard.setVisibility(View.VISIBLE);
        } else {
            itemDetailsCard.setVisibility(View.GONE);
        }
    }

    @Override
    public void onLoadJobOrderDetails(List<JobOrderHasDetailsModel> jobOrderHasDetailModels) {
        itemDetailsAdapter.setJobOrderHasDetails(jobOrderHasDetailModels);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onLoadJobOrders(List<JobOrderModel> jobOrderModels) {

    }
}
