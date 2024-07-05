package com.example.epod.job_management.job_order;

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
import androidx.recyclerview.widget.RecyclerView;

import com.example.epod.R;
import com.example.epod.job_management.job_order.controller.DataLoadCallback;
import com.example.epod.job_management.job_order.controller.JobOrderController;
import com.example.epod.job_management.job_order.view.adapter.JobOrderDetailsAdapter;
import com.example.epod.job_management.job_order.view.model.JobOrder;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.List;

public class JobOrderDetailsActivity extends AppCompatActivity implements DataLoadCallback {
    private JobOrderDetailsAdapter jobOrderDetailsAdapter;
    private JobOrderController jobOrderController;
    private LinearLayout itemDetailsLayout;
    private ViewSwitcher viewSwitcher;
    private ShimmerFrameLayout loadingLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.job_order_activity_job_order_details);

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
        jobOrderDetailsAdapter = new JobOrderDetailsAdapter(this);
        jobOrderController = new JobOrderController(jobOrderDetailsAdapter, this);
        jobOrderController.getUpdateJobOrder(jobOrderId);

        //  Set RecyclerView for job order details
        itemDetailsLayout = findViewById(R.id.itemDetails);
    }

    @Override
    public void onLoad(JobOrder jobOrder) {
        runOnUiThread(() -> {
            jobOrderDetailsAdapter.setJobOrder(jobOrder);
            Log.e("Response: ", "Loading Job order");
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
}
