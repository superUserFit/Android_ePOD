package com.example.epod.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.TextView;
import android.widget.Toast;

import com.example.epod.MainActivity;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;

public class Helper {
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
