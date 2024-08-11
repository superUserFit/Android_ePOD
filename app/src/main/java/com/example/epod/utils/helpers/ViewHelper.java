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

    public static class LoadingState {
        public final Status status;
        public final String message;

        private LoadingState(Status status, String message) {
            this.status = status;
            this.message = message;
        }

        public static LoadingState loading() {
            return new LoadingState(Status.LOADING, null);
        }

        public static LoadingState success() {
            return new LoadingState(Status.SUCCESS, null);
        }

        public static LoadingState error(String message) {
            return new LoadingState(Status.ERROR, message);
        }

        public enum Status {
            LOADING,
            SUCCESS,
            ERROR
        }
    }
}
