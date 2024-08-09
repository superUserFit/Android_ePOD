package com.example.epod.utils.helpers;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.epod.MainActivity;

public class ViewHelper {
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
}
