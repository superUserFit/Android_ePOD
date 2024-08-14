package com.example.epod;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.epod.screens.auth.LoginActivity;
import com.example.epod.databinding.ActivityMainBinding;
import com.example.epod.screens.job_management.job.view.JobFragment;
import com.example.epod.screens.job_management.job_order.main.JobOrderFragment;
import com.example.epod.screens.profile.ProfileFragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import javax.inject.Inject;


public class MainActivity extends AppCompatActivity {
    @Inject
    SharedPreferences sharedPreferences;

    ActivityMainBinding dataBinding;
    boolean isUserAssignee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MyApplication) getApplication()).getAppComponent().inject(this);
        dataBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(dataBinding.getRoot());

        sharedPreferences = getSharedPreferences("auth", MODE_PRIVATE);
        String token = sharedPreferences.getString("token", null);
        String userRole = sharedPreferences.getString("user_role", null);

        if(token == null) {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
            return;
        }

        isUserAssignee = "ASSIGNEE".equalsIgnoreCase(userRole);

        if(isUserAssignee) {
            replaceFragment(new JobFragment());
        } else {
            replaceFragment(new JobOrderFragment());
        }

        dataBinding.bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.job_management_tab:
                    if(isUserAssignee) {
                        replaceFragment(new JobFragment());
                    } else {
                        replaceFragment(new JobOrderFragment());
                    }
                    break;

                case R.id.profile_tab:
                    replaceFragment(new ProfileFragment());
                    break;
            }

            return true;
        });
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_layout, fragment);
        fragmentTransaction.commit();
    }
}
