package com.example.epod.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.epod.MainActivity;
import com.example.epod.R;
import com.example.epod.auth.repository.AuthCallback;
import com.example.epod.auth.model.Auth;
import com.example.epod.auth.service.AuthService;
import com.example.epod.utils.Helper;

public class LoginActivity extends AppCompatActivity implements AuthCallback {
    private AuthService authService;
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
        loginButton.setBackgroundResource(R.drawable.ui_rounded_64_gradient_orange);

        authService = new AuthService(this);

        loginButton.setOnClickListener(view -> {
            String username = usernameTextField.getText().toString();
            String password = passwordTextField.getText().toString();

            if (username.isEmpty() || password.isEmpty()) {
                Helper.showToast(LoginActivity.this, "Username and password cannot be blank", "SHORT");
                return;
            }

            loginButton.setText("");
            loginButton.setBackgroundResource(R.drawable.gradient_white);
            progressBar.setVisibility(View.VISIBLE);

            authService.performLogin(username, password);
        });
    }

    @Override
    public void onLoadAuth(Auth authenticatedUser) {
        Log.e("User in Activity: ", authenticatedUser.getUsername());
        progressBar.setVisibility(View.GONE);
        loginButton.setText("Login");
        loginButton.setBackgroundResource(R.drawable.ui_rounded_64_gradient_orange);
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onError(String errorMessage) {
        progressBar.setVisibility(View.GONE);
        loginButton.setText("Login");
        loginButton.setBackgroundResource(R.drawable.ui_rounded_64_gradient_orange);
        Helper.showToast(LoginActivity.this, errorMessage, "SHORT");
    }
}
