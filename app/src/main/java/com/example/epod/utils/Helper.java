package com.example.epod.utils;

import android.content.Context;
import android.content.Intent;

import com.example.epod.MainActivity;

public class Helper {
    public static void goBackActivity(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
