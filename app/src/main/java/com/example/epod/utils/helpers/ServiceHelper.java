package com.example.epod.utils.helpers;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.widget.Toast;

import com.example.epod.MainActivity;
import com.example.epod.auth.service.AuthService;
import com.example.epod.auth.service.AuthServiceInterface;

public class ServiceHelper {
    @SuppressLint("StaticFieldLeak")
    private static ServiceHelper instance;
    private AuthServiceInterface authService;
    private boolean isBound = false;
    private Context context;

    private ServiceHelper() {
    }

    public static synchronized ServiceHelper getInstance() {
        if (instance == null) {
            instance = new ServiceHelper();
        }
        return instance;
    }

    public void bindAuthService(Context context) {
        if (!isBound) {
            this.context = context.getApplicationContext();
            Intent intent = new Intent(this.context, AuthService.class);
            this.context.bindService(intent, authServiceConnection, Context.BIND_AUTO_CREATE);
        }
    }

    public void unbindAuthService() {
        if (isBound) {
            context.unbindService(authServiceConnection);
            isBound = false;
        }
    }

    public static void goBackActivity(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public static void showToast(Context context, String text, String duration) {
        int toastDuration;

        switch (duration) {
            case "LONG":
                toastDuration = Toast.LENGTH_LONG;
                break;
            case "SHORT":
            default:
                toastDuration = Toast.LENGTH_SHORT;
                break;
        }

        Toast.makeText(context, text, toastDuration).show();
    }

    public String getAuthorization() {
        if (authService != null) {
            return authService.getAuthorization();
        } else {
            showToast(context, "Service not connected", "SHORT");
            return null;
        }
    }

    private final ServiceConnection authServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            AuthService.LocalBinder binder = (AuthService.LocalBinder) iBinder;
            authService = binder.getService();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            authService = null;
            isBound = false;
        }
    };
}
