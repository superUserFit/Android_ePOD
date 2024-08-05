package com.example.epod.auth.view;

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
import com.example.epod.auth.data.model.Auth;
import com.example.epod.auth.data.repository.AuthCallback;
import com.example.epod.auth.view.model.AuthViewModel;
import com.example.epod.utils.Helper;

public class LoginActivity extends AppCompatActivity implements AuthCallback {
    private AuthViewModel authViewModel;
    private ProgressBar progressBar;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.auth_activity_login);

        Log.e("Message", "Hello World");

        EditText usernameTextField = findViewById(R.id.username);
        EditText passwordTextField = findViewById(R.id.password);
        loginButton = findViewById(R.id.login_button);
        progressBar = findViewById(R.id.progressBar);
        loginButton.setBackgroundResource(R.drawable.ui_rounded_64_gradient_orange);

        authViewModel = new ViewModelProvider(this).get(AuthViewModel.class);

        authViewModel.getAuthenticatedUser().observe(this, authenticatedUser -> {
            if(authenticatedUser != null) {
                handleAuthSuccess(authenticatedUser);
            }
        });

        authViewModel.getErrorMessage().observe(this, errorMessage -> {
            if(errorMessage != null) {
                handleAuthError(errorMessage);
            }
        });

        loginButton.setOnClickListener(view -> {
            String username = usernameTextField.getText().toString();
            String password = passwordTextField.getText().toString();

            Log.e("Activity: ", username);

            if (username.isEmpty() || password.isEmpty()) {
                Helper.showToast(LoginActivity.this, "Username and password cannot be blank", "SHORT");
                return;
            }

            authViewModel.login(username, password);

            loginButton.setEnabled(false);
            loginButton.setText("");
            loginButton.setBackgroundColor(R.color.white);
            progressBar.setVisibility(View.VISIBLE);
        });
    }

    @Override
    public void onLogin(Auth authenticatedUser) {
        Log.e("User in Activity: ", authenticatedUser.getUsername());
        progressBar.setVisibility(View.GONE);
        loginButton.setEnabled(true);
        loginButton.setText("Login");
        loginButton.setBackgroundResource(R.drawable.ui_rounded_64_gradient_orange);
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onError(String errorMessage) {
        progressBar.setVisibility(View.GONE);
        loginButton.setEnabled(true);
        loginButton.setText("Login");
        loginButton.setBackgroundResource(R.drawable.ui_rounded_64_gradient_orange);
        Helper.showToast(LoginActivity.this, errorMessage, "SHORT");
    }

    private void handleAuthSuccess(Auth authenticatedUser) {
        progressBar.setVisibility(View.GONE);
        loginButton.setEnabled(true);
        loginButton.setText("Login");
        loginButton.setBackgroundResource(R.drawable.ui_rounded_64_gradient_orange);
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void handleAuthError(String errorMessage) {
        progressBar.setVisibility(View.GONE);
        loginButton.setEnabled(true);
        loginButton.setText("Login");
        loginButton.setBackgroundResource(R.drawable.ui_rounded_64_gradient_orange);
        Helper.showToast(LoginActivity.this, errorMessage, "SHORT");
    }
}
