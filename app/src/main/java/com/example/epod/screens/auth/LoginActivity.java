package com.example.epod.screens.auth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.epod.MainActivity;
import com.example.epod.R;
import com.example.epod.application.auth.data.model.Auth;
import com.example.epod.screens.auth.model.AuthViewModel;
import com.example.epod.utils.helpers.ViewHelper;


public class LoginActivity extends AppCompatActivity {
    private AuthViewModel authViewModel;
    private ProgressBar progressBar;
    private Button loginButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.auth_activity_login);

        EditText usernameTextField = findViewById(R.id.username);
        EditText passwordTextField = findViewById(R.id.password);
        loginButton = findViewById(R.id.login_button);
        progressBar = findViewById(R.id.progressBar);

        authViewModel = new ViewModelProvider(this).get(AuthViewModel.class);

        authViewModel.getAuthenticatedUser().observe(this, authenticatedUser -> {
            if(authenticatedUser != null) {
                handleAuthSuccess(authenticatedUser);
            }
        });

        authViewModel.getLoadingState().observe(this, this::updateLoadingState);

        loginButton.setOnClickListener(view -> {
            String username = usernameTextField.getText().toString();
            String password = passwordTextField.getText().toString();

            if (username.isEmpty() || password.isEmpty()) {
                ViewHelper.showToast(LoginActivity.this, "Username and password cannot be blank", "SHORT");
                return;
            }

            authViewModel.login(username, password);
        });
    }

    private void handleAuthSuccess(Auth authenticatedUser) {
        navigateToMainActivity();
    }

    private void handleAuthError(String errorMessage) {
        ViewHelper.showToast(LoginActivity.this, errorMessage, "SHORT");
    }

    private void updateLoadingState(ViewHelper.LoadingState loadingState) {
        if (loadingState != null) {
            switch (loadingState.status) {
                case LOADING:
                    loginButton.setEnabled(false);
                    loginButton.setText("");
                    loginButton.setBackgroundResource(R.drawable.ui_rounded_64_white);
                    progressBar.setVisibility(View.VISIBLE);
                    break;

                case SUCCESS:
                    progressBar.setVisibility(View.GONE);
                    loginButton.setEnabled(true);
                    loginButton.setText("Login");
                    loginButton.setBackgroundResource(R.drawable.ui_rounded_64_gradient_orange);
                    break;

                case ERROR:
                    progressBar.setVisibility(View.GONE);
                    loginButton.setEnabled(true);
                    loginButton.setText("Login");
                    loginButton.setBackgroundResource(R.drawable.ui_rounded_64_gradient_orange);
                    ViewHelper.showToast(LoginActivity.this, loadingState.message, "SHORT");
                    break;
            }
        }
    }

    private void navigateToMainActivity() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}

