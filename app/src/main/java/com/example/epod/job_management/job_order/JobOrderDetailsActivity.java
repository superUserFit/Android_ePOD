package com.example.epod.job_management.job_order;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.epod.R;
import com.example.epod.job_management.job_order.controller.DataLoadCallback;
import com.example.epod.job_management.job_order.controller.JobOrderController;
import com.example.epod.job_management.job_order.view.adapter.JobOrderHasDetailsAdapter;
import com.example.epod.job_management.job_order.view.model.JobOrder;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.ArrayList;
import java.util.List;

public class JobOrderDetailsActivity extends AppCompatActivity implements DataLoadCallback {
    private TextView docNo, docDate, deadline, tripType, description, customerName, attentionName;
    private TextView phoneNo, address, containerNo, containerSize, containerType, deliveryAddress;


    private JobOrderHasDetailsAdapter jobOrderHasDetailsAdapter;
    private JobOrderController jobOrderController;
    private LinearLayout itemDetailsLayout;
    private ViewSwitcher viewSwitcher;
    private ShimmerFrameLayout loadingLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.job_order_activity_job_order_details);
        setLayout();

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
        jobOrderHasDetailsAdapter = new JobOrderHasDetailsAdapter(new ArrayList<>(), this);
        jobOrderController = new JobOrderController(jobOrderHasDetailsAdapter, this);
        jobOrderController.getUpdateJobOrder(jobOrderId);

        //  Set RecyclerView for job order details
        jobOrderController.getUpdateJobOrderHasDetails(jobOrderId);
        itemDetailsLayout = findViewById(R.id.itemDetails);
    }

    @Override
    public void onLoad(JobOrder jobOrder) {
        runOnUiThread(() -> {
            docNo.setText(jobOrder.getDocNo() != null ? jobOrder.getDocNo() : "");
            docDate.setText(jobOrder.getDocDate() != null ? jobOrder.getDocDate() : "");
            deadline.setText(jobOrder.getDeadline() != null ? jobOrder.getDeadline() : "");
            tripType.setText(jobOrder.getTripType() != null ? jobOrder.getTripType() : "");
            description.setText(jobOrder.getDescription() != null ? jobOrder.getDescription() : "");
            Log.e("Response: ", "Loading Job order: " + jobOrder.getDocNo());
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void expandItemDetails(View view) {
        if (itemDetailsLayout.getVisibility() == View.GONE) {
            itemDetailsLayout.setVisibility(View.VISIBLE);
        } else {
            itemDetailsLayout.setVisibility(View.GONE);
        }
    }

    @Override
    public void onLoad(List<JobOrder> jobOrders) {

    }

    private void setLayout() {
        docNo = findViewById(R.id.docNo);
        docDate = findViewById(R.id.docDate);
        deadline = findViewById(R.id.deadline);
        tripType = findViewById(R.id.tripType);
        description = findViewById(R.id.description);
    }
}
