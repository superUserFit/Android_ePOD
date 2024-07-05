package com.example.epod.job_management;

import android.content.res.Configuration;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.Toolbar;

import com.example.epod.R;
import com.example.epod.job_management.view.adapter.JobManagementAdapter;
import com.example.epod.job_management.view.model.JobManagement;

import java.util.ArrayList;
import java.util.List;

public class JobManagementFragment extends Fragment {

    public JobManagementFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.job_management_fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Toolbar toolbar = view.findViewById(R.id.toolbar);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if (activity != null) {
            activity.setSupportActionBar(toolbar);
            if (activity.getSupportActionBar() != null) {
                activity.getSupportActionBar().setDisplayShowTitleEnabled(false);
            }
        }

        List<JobManagement> jobManagementList = new ArrayList<JobManagement>();

        jobManagementList.add(new JobManagement("Job Order", R.drawable.icon_work));
        jobManagementList.add(new JobManagement("Job", R.drawable.icon_assignment));

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView_jobManagement);

        int itemCount = isLargerScreen() ? 3 : 2;
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), itemCount);

        recyclerView.setAdapter(new JobManagementAdapter(getContext(), jobManagementList));
        recyclerView.setLayoutManager(layoutManager);
    }

    private boolean isLargerScreen() {
        return (getResources().getConfiguration().screenLayout
        & Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }
}
